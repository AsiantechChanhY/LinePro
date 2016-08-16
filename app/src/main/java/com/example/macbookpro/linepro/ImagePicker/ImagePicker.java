/*
 * Copyright 2016 Mario Velasco Casquero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.macbookpro.linepro.ImagePicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagePicker {

    private static final int PICK_IMAGE_ID = 234;
    private static final String TAG = ImagePicker.class.getSimpleName();
    private static final String TEMP_IMAGE_NAME = "tempImage";

    public static void pickImage(Activity activity, String chooserTitle) {
        Intent chooseImageIntent = getPickImageIntent(activity, chooserTitle);
        activity.startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }

    public static Intent getPickImageIntent(Context context, String chooserTitle) {
        Intent chooserIntent = null;
        List<Intent> intentList = new ArrayList<>();

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTemporalFile(context)));
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    chooserTitle);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    intentList.toArray(new Parcelable[intentList.size()]));
        }

        return chooserIntent;
    }

    private static List<Intent> addIntentsToList(Context context, List<Intent> list, Intent intent) {
        Log.i(TAG, "Adding intents of type: " + intent.getAction());
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
            Log.i(TAG, "App package: " + packageName);
        }
        return list;
    }

    private static File getTemporalFile(Context context) {
        return new File(context.getExternalCacheDir(), TEMP_IMAGE_NAME);
    }

}

