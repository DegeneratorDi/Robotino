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

    public boolean getStatusButton(String key){
                switch (key){
                    case "up":{
                        return (devOut[1]==0) ?  true : false;
                    }

                    case "down":{
                        return (devOut[1]==-1) ?  true : false;
                    }

                    case "left":{
                        return (devOut[0]==0) ?  true : false;
                    }

                    case "right":{
                        return (devOut[0]==-1) ?  true : false;
                    }

                    case "1":{
                        return (devOut[5]==31) ?  true : false;
                    }

                    case "2":{
                        return (devOut[5]==47) ?  true : false;
                    }

                    case "3":{
                        return (devOut[5]==79) ?  true : false;
                    }

                    case "4":{
                        return (devOut[5]==-113) ?  true : false;
                    }

                    case "LT":{
                        return (devOut[6]==1) ?  true : false;
                    }

                    case "RT":{
                        return (devOut[6]==2) ?  true : false;
                    }

                    case "LB":{
                        return (devOut[6]==4) ?  true : false;
                    }

                    case "RB":{
                        return (devOut[6]==8) ?  true : false;
                    }

                    case "9":{
                        return (devOut[6]==16) ?  true : false;
                    }

                    case "10":{
                        return (devOut[6]==32) ?  true : false;
                    }
                }
        return false;
    }



    void outDevise() {
        for (int i = 0; i < devOut.length; i++){
                System.out.printf("%02d ", devOut[i]);

        }

        System.out.println();
    }


}
