import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Computer {
    private String processor;
    private String osName;
    private String systemArch;
    private String RAM;
    private String hardDisk;

    /**
     * Constructor initialize information
     */
    public Computer() throws IOException, InterruptedException{
        this.processor = System.getenv("PROCESSOR_ARCHITEW6432");
        this.osName = System.getProperty("os.name");
        this.systemArch = System.getProperty("os.arch");
        this.RAM = ramInitialize();
        this.hardDisk = hardDiskInitialize();
    }


    public String getProcessor() {
        return processor;
    }

    public String getOsName() {
        return osName;
    }

    public String getSystemArch() {
        return systemArch;
    }

    public String getRAM() {
        return RAM;
    }

    public void ComputerInformation() {
        System.out.println("Os name: " + getOsName());
        System.out.println("System architecture information: " + getSystemArch());
        System.out.println(getRAM());
        System.out.println(getHardDisk());
    }


    public String getHardDisk() {
        return hardDisk;
    }

    private String ramInitialize() throws InterruptedException, IOException{
        if(System.getProperty("os.name").equals("Linux")) {
            Process p = Runtime.getRuntime().exec("free -h");

            p.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line = "";
            String output = "";

            while ((line = buf.readLine()) != null) {
                output += line + "\n";
            }

            return "\n\n" + "==============Ram Infromation================\n" + output;
        } else {
            return "Unknown value";
        }
    }

    private String hardDiskInitialize() throws InterruptedException, IOException{
        Process process = Runtime.getRuntime().exec("free -h");

        process.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String line = "";
        String output = "";

        while ((line = buf.readLine()) != null) {
            output += line + "\n";
        }

        return "\n" + "==============HArd Disk Infromation================\n" + output;
    }


    public static void main(String[] args) throws IOException, InterruptedException{
        Computer comp = new Computer();
        comp.ComputerInformation();
    }
}
