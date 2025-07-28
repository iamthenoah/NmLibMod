package com.example.examplemod;

public enum NmPacket {
    OFF(true, (byte) 0x9C, (byte) 0x6E, (byte) 0x0B, (byte) 0x3D),
    MODE_1(false, (byte) 0x15, (byte) 0x6F, (byte) 0x0B, (byte) 0x2C),
    MODE_2(false, (byte) 0x8E, (byte) 0x6C, (byte) 0x0B, (byte) 0x1E),
    MODE_3(false, (byte) 0x07, (byte) 0x6D, (byte) 0x0B, (byte) 0x0F),
    MODE_4(false, (byte) 0xB8, (byte) 0x6A, (byte) 0x0B, (byte) 0x7B),
    MODE_5(false, (byte) 0x31, (byte) 0x6B, (byte) 0x0B, (byte) 0x6A),
    MODE_6(false, (byte) 0xAA, (byte) 0x68, (byte) 0x0B, (byte) 0x58),
    MODE_7(false, (byte) 0x23, (byte) 0x69, (byte) 0x0B, (byte) 0x49),
    MODE_8(false, (byte) 0xD4, (byte) 0x66, (byte) 0x0B, (byte) 0xB1),
    MODE_9(false, (byte) 0x13, (byte) 0x12, (byte) 0x0B, (byte) 0xA0);

    static boolean DEBOUNCE;
    
    final boolean isStop;
    final byte[] payload;
    
    NmPacket(boolean isStop, byte a1, byte a2, byte b1, byte b2) {
        this.isStop = isStop;
        this.payload = new byte[] {
                (byte) 0x08, (byte) 0x49, (byte) 0x23, (byte) 0xAE, (byte) 0xCB, (byte) 0xC1, (byte) 0xD1,
                a2, a1, b2, b1,
                (byte) 0x0C, (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10, (byte) 0x11, (byte) 0x12, 
                (byte) 0x13, (byte) 0x14, (byte) 0x15, (byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19
        };
    }
    
    public void send(int duration) {
        if (!DEBOUNCE || this == OFF) {
            DEBOUNCE = true;

            new Thread(() -> {
                try {
                    BleAdvertiser.StartBLEAdvert(0xFFF0, payload, new byte[0]);
                    Thread.sleep(duration);
                    BleAdvertiser.StopBLEAdvert();
                    
                    if (!isStop) OFF.send(duration);
                } catch (Exception exception) {
                    // do nothing
                } finally {
                    DEBOUNCE = false;
                }
            }).start();
        }
    }
}
