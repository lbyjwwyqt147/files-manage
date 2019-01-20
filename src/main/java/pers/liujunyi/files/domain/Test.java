package pers.liujunyi.files.domain;

public class Test {

    public static void main(String[] strings) {
        String fileSeparator = System.getProperty("file.separator");
        System.out.println(fileSeparator);
        String path = "E://fileUpload/";
        System.out.println(path.indexOf("//"));
        System.out.println(path.indexOf("/"));

        String result = path;
        if (fileSeparator.equals("\\")) {
            path = path.replace("//", "/");
            result = path.replace("/", "\\");
        }
        System.out.println(result);
    }
}
