package com.janbar.shutdown;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isAccessibilitySettingsOn()) {
            startService(new Intent(this, ShutdownService.class));
        } else {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivity(intent);
        }
        finish();
    }

    public boolean isAccessibilitySettingsOn() {
        int accessibilityEnabled = 0;
        ContentResolver cr = getApplicationContext().getContentResolver();
        try {
            accessibilityEnabled = Settings.Secure.getInt(cr, Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException ignored) {
        }
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(cr, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            return settingValue != null && settingValue.endsWith(":com.janbar.shutdown/com.janbar.shutdown.ShutdownService");
        }
        return false;
    }
}
