package com.janbar.shutdown;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;

public class ShutdownService extends AccessibilityService {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            performGlobalAction(AccessibilityService.GLOBAL_ACTION_POWER_DIALOG);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {
    }
}
