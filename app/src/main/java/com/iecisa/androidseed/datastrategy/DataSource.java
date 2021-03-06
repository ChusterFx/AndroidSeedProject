package com.iecisa.androidseed.datastrategy;

import androidx.annotation.NonNull;

import com.iecisa.androidseed.BuildConfig;

public enum DataSource {
    DATA_WS("ws"),
    DATA_MOCK("mock");

    public static final String WS_DATA_ORIGIN = "WS";
    public static final String MOCK_DATA_ORIGIN = "MOCK";

    private final String dataSource;

    DataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public String toString() {
        return dataSource;
    }

    public static DataSource getDefaulDataSource() {
        return buildFromString(BuildConfig.DATA_SOURCE);
    }

    public static DataSource buildFromString(String source) {
        switch (source) {
            case WS_DATA_ORIGIN:
                return DATA_WS;
            case MOCK_DATA_ORIGIN:
                return DATA_MOCK;
            default:
                return DATA_WS;
        }
    }
}
