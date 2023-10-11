import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
        ArrayList<String> strings = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return Integer.toString(strings.size()) + " strings in server";
        } else {
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                strings.add(parameters[1]);
                return "String added!";
            }
            else if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                String s = parameters[1];
                StringBuilder b = new StringBuilder();
                for (int i = 0; i < strings.size(); ++i) {
                        if (strings.get(i).contains(s)) {
                                b.append(strings.get(i));
                                b.append("\n");
                        }
                }
                return b.toString();
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
