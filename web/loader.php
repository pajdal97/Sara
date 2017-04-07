<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 15:05
 */
include './maincore.php';

$pathOrigin = $_GET['SARA_path'];
$path = explode("/",$pathOrigin);
if($path[count($path)-1] == "") {
    $pathOrigin .= "index";
}
if($pathOrigin == "") $pathOrigin = "index";

switch($path[0]) {
    case "api":
        header("Content-Type: text/json"); // Set header
        if(isset($_GET['token'])) {
            $token = $db->query("SELECT * FROM users WHERE token='".$_GET['token']."' LIMIT 1")->fetch_assoc();
            if($token['id'] > 0) {
                $result['status'] = "success"; // Default success

                if ($path[1] == true) {
                    include './api/' . $path[1] . '.php';
                } else {
                    $result['error_type'] = "wrong_action";
                }
            } else {
                $result['error_message'] = "wrong_token";
            }
        } else {
            $result['error_message'] = "not_defined_token";
        }

        if (count($result['error_message']) > 0) $result['status'] = "error"; // If error message
        echo json_encode($result); // Print all result

        break;
    default:
        include './include/head.php';
        include './pages/'.$pathOrigin.".php";
        break;
}
