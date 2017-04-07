<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 15:33
 */

switch($_GET['type']) {
    case "door":
        if(in_array($_GET['find_by'],array("id","name","type","recently_update"))) {
            switch($_GET['find_by']) {
                case "id":

                    break;
                case "name":

                    break;
                case "type":

                    break;
                case "recently_update":

                    break;
            }
        } else {
            $result['error_message'][] = "Wrong value in 'find_by'";
        }
        break;

    default:
        $result['error_message'][] = "Not defined action in 'type'";
        break;
}