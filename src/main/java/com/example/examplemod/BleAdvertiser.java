package com.example.examplemod;

public final class BleAdvertiser {

    static {
        DllUtil.load(DllUtil.dllArch(), "BleAdvertiser.dll");
    }

    private BleAdvertiser() {
        // lock instantiation
    }

    /**
     * Starts BLE advertising with manufacturer-specific data and service UUIDs.
     *
     * @param companyId        The 16-bit Bluetooth SIG-assigned Company Identifier.
     * @param manufacturerData Raw manufacturer-specific data (excluding the company ID).
     *                         This will be prefixed with the company ID and included as the manufacturer data field in the advertisement.
     * @param serviceUuids     An array of 16-byte UUIDs (in little-endian format) to include as service UUIDs in the advertisement payload.
     *                         Multiple UUIDs can be provided by concatenating them.
     */
    public static native void StartBLEAdvert(int companyId, byte[] manufacturerData, byte[] serviceUuids);

    /**
     * Stops the ongoing BLE advertising session.
     */
    public static native void StopBLEAdvert();
}
