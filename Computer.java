import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Computer {
    private String processor;
    private String osName;
    private String systemArch;
    private String RAM;

    /**
     * Constructor initialize information
     */
    public Computer() throws IOException, InterruptedException{
        this.processor = System.getenv("PROCESSOR_ARCHITEW6432");
        this.osName = System.getProperty("os.name");
        this.systemArch = System.getProperty("os.arch");
        this.RAM = ramInitialize();
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
        System.out.println("RAM information: " + getRAM());
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

            return "\n" + "==============Ram Infromation================\n" + output;
        } else {
            return "Unknown value";
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException{
        Computer comp = new Computer();
        comp.ComputerInformation();
    }
}
