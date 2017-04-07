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
if($pathOrigin == "") $pathOrigin = "index";

switch($path[0]) {
    case "api":
        header("Content-Type: text/json"); // Set header
        if(isset($_GET['token'])) {
            $result['status'] = "success"; // Default success

            if ($path[1] == true) {
                include './api/' . $path[1] . '.php';
            } else {
                $result['error_type'][] = "wrong_action";
            }
        } else {
            $result['error_message'] = "wrong_token";
        }

        if (count($result['error_message']) > 0) $result['status'] = "error"; // If error message
        echo json_encode($result); // Print all result

        break;
    default:
        include './' . explode("?", $pathOrigin)[0] . ".php";
        break;
}
