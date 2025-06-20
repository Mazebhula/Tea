package org.denzhe.tea;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class HomeController {
    ArrayList<String> users;

    {

        try {
            new EstablishDb();
            users = new dbcon().getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users")
    public String index(){
        return "{users: "+users.toString()+"}";
    }
    @PostMapping("/users")
    public String addUser(@RequestBody String user){
        if (users.contains(user)){
            return "Error";
        }
        users.add(user);
        try {
            new UpdateDb().addUsers(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "200";
    }
    @DeleteMapping("users")
    public String removeUser(@RequestBody String user){
        if (users.contains(user)){
            users.remove(user);
            return "200";
        }else{
            return "the user " + user + " not in record";
        }
    }
    @PutMapping("/{id}")
    public String updateUsers(@RequestBody String user,@PathVariable String id){
        if (users.contains(id)){
            users.remove(id);
            users.add(user);

            return "200";
        }else{
            return "user " + user + " not in record";
        }
    }
    @GetMapping("/")
    public String home(){
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Title</title>
                </head>
                <body>
                <h1>            Hello           </h1>
                <h1> </h1>
                <h2> please ensure you use the correct endpoint </h2>
                </body>
                </html>
                """;
    }


}
