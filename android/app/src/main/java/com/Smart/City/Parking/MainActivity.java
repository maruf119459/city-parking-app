package com.smart.city.parking;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Find the Capacitor WebView
        WebView webView = getBridge().getWebView();

        // 2. Initialize SwipeRefreshLayout
        swipeRefreshLayout = new SwipeRefreshLayout(this);

        // Optional: Set the colors of the refresh circle (Matches your app theme)
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // 3. Set the Refresh Listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Reload the web content
                webView.reload();
                
                // Stop the spinning animation after a short delay (or wait for page load)
                webView.postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 1000);
            }
        });

        // 4. Inject the SwipeRefreshLayout into the view hierarchy
        android.view.ViewGroup parent = (android.view.ViewGroup) webView.getParent();
        if (parent != null) {
            parent.removeView(webView);
            swipeRefreshLayout.addView(webView);
            parent.addView(swipeRefreshLayout);
        }

        // 5. Handle scroll conflict (Disable refresh if user is not at the very top)
        webView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            swipeRefreshLayout.setEnabled(webView.getScrollY() == 0);
        });
    }
}