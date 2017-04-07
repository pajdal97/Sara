<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 15:33
 */

switch($_GET['type']) {
    case "door":
        if(in_array($_GET['status'],array("open","close","middle"))) {
            $db->query("UPDATE objects SET data = '" . $_GET['status'] . "' ");
        } else {
            $result['error_message'][] = "wrong_value";
        }
        break;

    default:
        $result['error_message'][] = "not_defined_action";
        break;
}