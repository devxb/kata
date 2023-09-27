package extensionjava;

public class ExtensionTarget {

    public String hello() {
        return "hello";
    }

    public static void main(String[] args) {
        ExtensionTarget extensionTarget = new ExtensionTarget();

        System.out.println(Extension.bye(extensionTarget));
    }

}
