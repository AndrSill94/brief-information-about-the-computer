import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HardDisk {
    private String description;
    private String product;
    private String vendor;


    /**
     *
     * @throws IOException
     * @throws InterruptedException
     */
    private void processHardDiskInformation() throws IOException, InterruptedException{
        String values = getHardDiskInformation();
        values = values.replace("\n", ",").replace(" ", "");
        String[] arrayValues = values.split(",");
        Map<String, String> hashMap = new HashMap<String, String>();

        for (int i = 0; i < arrayValues.length; i+=2) {
            String key = arrayValues[i];
            String val = arrayValues[i+1];
            hashMap.put(key, values);
        }


    }

    /**
     *
     * @return String output
     * @throws IOException
     * @throws InterruptedException
     */
    private String getHardDiskInformation() throws IOException, InterruptedException{
        Process process = Runtime.getRuntime().exec("lshw -class disk");

        process.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String line = "";
        String output = "";

        while ((line = buf.readLine()) != null) {
            output += line + "\n";
        }

        return output;

    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Hard Disk information";
    }


    public static void main(String[] args) throws IOException, InterruptedException{
        HardDisk hardDisk = new HardDisk();
        System.out.println(hardDisk);
        hardDisk.processHardDiskInformation();
    }
}
