package com.example.gk_ltdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gk_ltdd.model.Product;
import com.example.gk_ltdd.model.User;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gk_ltdd.db";
    private static final int DATABASE_VERSION = 1;

    // Table User
    private static final String TABLE_USER = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_IS_ACTIVATED = "is_activated";
    private static final String COLUMN_OTP = "otp";

    // Table Product
    private static final String TABLE_PRODUCT = "products";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_PRODUCT_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_USERNAME + " TEXT UNIQUE, "
                + COLUMN_EMAIL + " TEXT UNIQUE, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_IMAGE + " TEXT, "
                + COLUMN_IS_ACTIVATED + " INTEGER DEFAULT 0, "
                + COLUMN_OTP + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_CATEGORY + " TEXT, "
                + COLUMN_PRICE + " REAL, "
                + COLUMN_PRODUCT_IMAGE + " TEXT)";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);

        // Thêm tài khoản mặc định để đăng nhập
        db.execSQL("INSERT INTO users (name, username, password) VALUES ('Admin', 'admin', '123456')");
    }

    // 📌 Đăng ký tài khoản
    public boolean insertUser(String name, String username, String email, String password, String image, String otp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_IMAGE, image);
        values.put(COLUMN_OTP, otp);
        long result = db.insert(TABLE_USER, null, values);
        db.close();
        return result != -1;
    }

    // 📌 Lấy thông tin người dùng theo username
    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OTP)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_ACTIVATED)) == 1
            );
            cursor.close();
            db.close();
            return user;
        }
        return null;
    }

    // 📌 Cập nhật OTP cho người dùng
    public boolean updateUserOTP(String email, String otp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_OTP, otp);
        int result = db.update(TABLE_USER, values, COLUMN_EMAIL + "=?", new String[]{email});
        db.close();
        return result > 0;
    }

    // 📌 Kích hoạt tài khoản bằng OTP
    public boolean verifyOTP(String email, String otp) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, COLUMN_EMAIL + "=? AND " + COLUMN_OTP + "=?", new String[]{email, otp}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return activateUser(email);
        }
        return false;
    }

    // 📌 Cập nhật trạng thái kích hoạt tài khoản
    public boolean activateUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_ACTIVATED, 1);
        int result = db.update(TABLE_USER, values, COLUMN_EMAIL + "=?", new String[]{email});
        db.close();
        return result > 0;
    }

    // 📌 Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT, null);
        if (cursor.moveToFirst()) {
            do {
                products.add(new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }

    // 📌 Lấy sản phẩm mới nhất
    public Product getLastProduct() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT + " ORDER BY " + COLUMN_PRODUCT_ID + " DESC LIMIT 1", null);
        if (cursor.moveToFirst()) {
            Product product = new Product(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE))
            );
            cursor.close();
            db.close();
            return product;
        }
        return null;
    }

    // 📌 Lấy tất cả danh mục sản phẩm
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + COLUMN_CATEGORY + " FROM " + TABLE_PRODUCT, null);
        while (cursor.moveToNext()) {
            categories.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return categories;
    }
}
