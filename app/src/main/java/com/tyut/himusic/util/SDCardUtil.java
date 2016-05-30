/*******************************************************************************
 * Recruit Andriod
 * Created by lqc
 * Copyright © 2016年 issuebang.com. All rights reserved.
 ******************************************************************************/

package com.tyut.himusic.util;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;

/**
 * 有关SD卡路径工具类
 */
@SuppressLint("NewApi")
public class SDCardUtil
{

    private static final String dirName = "com.tyut.himusic";

    /**
     * 获取sd卡路径
     */
    public static String getStoragePath()
    {
        if (hasSdcard())
        {
            return Environment.getExternalStorageDirectory().getPath();// 获取SD卡根目录
        } else
        {
            return Environment.getDataDirectory().getPath();  //获取手机自带存储根目录
        }
    }

    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public static boolean hasSdcard()
    {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 照片存储路径
     *
     * @return
     */
    public static String getCameraPath()
    {
        String path = null;
        if (hasSdcard())
        {
            path = getStoragePath() + "/" + dirName + "/" + "Camera";
            File file = new File(path);
            // 若不存在，则自动创建
            if (!file.exists())
            {
                file.mkdirs();
            }
        }
        return path;
    }

    /**
     * 图片存储路径
     *
     * @return
     */
    public static String getImagePath()
    {
        String path = null;
        if (hasSdcard())
        {
            path = getStoragePath() + "/" + dirName + "/" + "Image";
            File file = new File(path);
            // 若不存在，则自动创建
            if (!file.exists())
            {
                file.mkdirs();
            }
        }
        return path;
    }

    public static String getVideoPath(){
        String path = null;
        if (hasSdcard())
        {
            path = getStoragePath() + "/" + dirName + "/" + "Video";
            File file = new File(path);
            // 若不存在，则自动创建
            if (!file.exists())
            {
                file.mkdirs();
            }
        }
        return path;
    }

    /**
     * 头像储存路径
     *
     * @return
     */
    public static String getProfilePath()
    {
        String path = null;
        if (hasSdcard())
        {
            path = getStoragePath() + "/" + dirName + "/" + "profile";
            File file = new File(path);
            // 若不存在，则自动创建
            if (!file.exists())
            {
                file.mkdirs();
            }
        }
        return path;
    }

    /**
     * 异常log储存路径
     *
     * @return
     */
    public static String getCrashPath()
    {
        String path = null;
        if (hasSdcard())
        {
            path = getStoragePath() + "/" + dirName + "/" + "crash";
            File file = new File(path);
            // 若不存在，则自动创建
            if (!file.exists())
            {
                file.mkdirs();
            }
        }
        return path;
    }

    /**
     * 获取相册选择图片路径4.4以上和4.4以下的区分
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getPath(final Context context, final Uri uri)
    {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri))
        {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri))
            {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type))
                {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri))
            {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri))
            {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type))
                {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type))
                {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type))
                {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme()))
        {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme()))
        {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs)
    {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try
        {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst())
            {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally
        {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri)
    {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri)
    {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri)
    {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri)
    {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}