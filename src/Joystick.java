import purejavahidapi.*;

import java.io.IOException;
import java.util.List;

public class Joystick {

    private List<HidDeviceInfo> devList;
    private HidDevice devise;
    private byte[] devOut;

    Joystick(){
        devList = PureJavaHidApi.enumerateDevices();
        devOut=new byte[8];
    }

    void getHidDevices() {


        for (HidDeviceInfo info : devList) {
            System.out.printf("VID = 0x%04X PID = 0x%04X Manufacturer = %s Product = %s Path = %s\n", //
                    info.getVendorId(), //
                    info.getProductId(), //
                    info.getManufacturerString(), //
                    info.getProductString(), //
                    info.getPath());
        }
    }

    void setDevise(int num) throws IOException {

        devise = PureJavaHidApi.openDevice(devList.get(num));
    }

    void run(){
        devise.setInputReportListener(new InputReportListener() {
            @Override
            public void onInputReport(HidDevice source, byte Id, byte[] data, int len) {
                devOut = data;
            }
        });

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    void test(int num) {
        for (int j = 0; j < num; j++) {
            for (int i = 0; i < 8; i++)
                System.out.printf("%02d ", devOut[i]);
                System.out.println();


        }
    }


}
