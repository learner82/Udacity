package com.example.android.myinventory;


import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myinventory.data.ProductContract;

public class EditorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_PRODUCT_LOADER = 1;
    Uri imageUri;
    private ImageView mImage;
    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mSupplierName;
    private EditText mSupplierEmail;
    private TextView mQuantityTextView;
    private Button mPlusButton;
    private Button mMinusButton;
    private TextView mImageText;
    private int mQuantity;
    private Uri mCurrentProductUri;

    private boolean mProductHasChanged = false;

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mProductHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        mImage = (ImageView) findViewById(R.id.product_picture);
        mNameEditText = (EditText) findViewById(R.id.edit_product_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_product_price);
        mSupplierName = (EditText) findViewById(R.id.supplier_name);
        mSupplierEmail = (EditText) findViewById(R.id.supplier_email);
        mQuantityTextView = (TextView) findViewById(R.id.edit_quantity_text_view);
        mPlusButton = (Button) findViewById(R.id.button_plus);
        mMinusButton = (Button) findViewById(R.id.button_minus);
        mImageText = (TextView) findViewById(R.id.add_photo_text);

        if (mCurrentProductUri == null) {
            setTitle(getString(R.string.add_product_title));
            mImageText.setText(getString(R.string.add_photo_text));
            mSupplierName.setEnabled(true);
            mSupplierEmail.setEnabled(true);
            mMinusButton.setVisibility(View.VISIBLE);
            mPlusButton.setVisibility(View.VISIBLE);
            mQuantityTextView.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.edit_product_title));
            mImageText.setText(getString(R.string.change_photo_text));
            mMinusButton.setVisibility(View.VISIBLE);
            mPlusButton.setVisibility(View.VISIBLE);
            mQuantityTextView.setVisibility(View.VISIBLE);
            mSupplierName.setEnabled(false);
            mSupplierEmail.setEnabled(false);
            getSupportLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
        }

        mNameEditText.setOnTouchListener(mOnTouchListener);
        mPriceEditText.setOnTouchListener(mOnTouchListener);
        mSupplierName.setOnTouchListener(mOnTouchListener);
        mSupplierEmail.setOnTouchListener(mOnTouchListener);
        mMinusButton.setOnTouchListener(mOnTouchListener);
        mPlusButton.setOnTouchListener(mOnTouchListener);

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trySelector();
                mProductHasChanged = true;
            }
        });
    }

    public void trySelector() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
        openSelector();
    }

    private void openSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType(getString(R.string.intentType));
        startActivityForResult(Intent.createChooser(intent, getString(R.string.selectPicture)),0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openSelector();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();
                mImage.setImageURI(imageUri);
                mImage.invalidate();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (mCurrentProductUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:

                if (saveProduct()) {
                    finish();
                }
                return true;

            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;

            case R.id.order_more:
                orderMore();
                return true;

            case android.R.id.home:

                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void orderMore() {
        Intent intent = new Intent(android.content.Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mail to:" + mSupplierEmail.getText().toString().trim()));
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "New Order");
        String message = "We need a new order of " + mNameEditText.getText().toString().trim();
        intent.putExtra(android.content.Intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    private boolean saveProduct() {

        boolean allOk = false;

        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        String supplierNameString = mSupplierName.getText().toString().trim();
        String supplierEmailString = mSupplierEmail.getText().toString().trim();
        String quantityString = mQuantityTextView.getText().toString();

        if (mCurrentProductUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(priceString) &&
                TextUtils.isEmpty(supplierNameString) && TextUtils.isEmpty(supplierEmailString) &&
                TextUtils.isEmpty(quantityString)&& imageUri == null) {

            allOk = true;
            return allOk;
        }

        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, getString(R.string.productNameReq), Toast.LENGTH_SHORT).show();
            return allOk;
        }

        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantityString);

        if (TextUtils.isEmpty(priceString)) {
            Toast.makeText(this, getString(R.string.productPriceReq), Toast.LENGTH_SHORT).show();
            return allOk;
        }

        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, priceString);

        if (TextUtils.isEmpty(supplierNameString)) {
            Toast.makeText(this, getString(R.string.supplierNameReq), Toast.LENGTH_SHORT).show();
            return allOk;
        }

        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, supplierNameString);

        if (TextUtils.isEmpty(supplierEmailString)) {
            Toast.makeText(this, getString(R.string.supplierEmailReq), Toast.LENGTH_SHORT).show();
            return allOk;
        }

        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL, supplierEmailString);

        if (imageUri == null) {
            Toast.makeText(this, getString(R.string.productPicReq), Toast.LENGTH_SHORT).show();
            return allOk;
        }

        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PICTURE, imageUri.toString());

        if (mCurrentProductUri == null) {

            Uri newUri = getContentResolver().insert(ProductContract.ProductEntry.CONTENT_URI, values);

            if (newUri == null) {

                Toast.makeText(this, getString(R.string.error_saving_data),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.data_saved),
                        Toast.LENGTH_SHORT).show();
            }
        } else {

            int rowsAffected = getContentResolver().update(mCurrentProductUri, values, null, null);

            if (rowsAffected == 0) {

                Toast.makeText(this, getString(R.string.error_saving_data),
                        Toast.LENGTH_SHORT).show();
            } else {

                if (mProductHasChanged) {
                    Toast.makeText(this, getString(R.string.data_saved),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        allOk = true;
        return allOk;
    }

    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {

        if (mCurrentProductUri != null) {

            int rowsDeleted = getContentResolver().delete(mCurrentProductUri, null, null);

            if (rowsDeleted == 0) {

                Toast.makeText(this, getString(R.string.editor_delete_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_delete_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,
                ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductContract.ProductEntry.COLUMN_PRODUCT_PICTURE,
                ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL};

        return new CursorLoader(this,
                mCurrentProductUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int pictureColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PICTURE);
            int sNameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int sEmailColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL);

            String name = cursor.getString(nameColumnIndex);
            String price = cursor.getString(priceColumnIndex);
            String sName = cursor.getString(sNameColumnIndex);
            String sEmail = cursor.getString(sEmailColumnIndex);
            mQuantity = cursor.getInt(quantityColumnIndex);
            String imageUriString = cursor.getString(pictureColumnIndex);

            mNameEditText.setText(name);
            mPriceEditText.setText(price);
            mSupplierName.setText(sName);
            mSupplierEmail.setText(sEmail);
            mQuantityTextView.setText(Integer.toString(mQuantity));
            imageUri = Uri.parse(imageUriString);
            mImage.setImageURI(imageUri);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNameEditText.setText("");
        mPriceEditText.setText("");
        mSupplierName.setText("");
        mSupplierEmail.setText("");
        mQuantityTextView.setText("");
    }

    public void plusButtonClicked(View view) {
        mQuantity++;
        displayQuantity();
    }

    public void minusButtonClicked(View view) {
        if (mQuantity == 0) {
            Toast.makeText(this, "Can't decrease quantity", Toast.LENGTH_SHORT).show();
        } else {
            mQuantity--;
            displayQuantity();
        }
    }

    public void displayQuantity() {
        mQuantityTextView.setText(String.valueOf(mQuantity));
    }
}