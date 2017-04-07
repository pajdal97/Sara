<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 15:33
 */

switch($_GET['type']) {
    case "door":

        // DOOR API

        if(in_array($_GET['status'],array("open","close","middle"))) {
            if(in_array($_GET['find_by'],array("id","name","type","recently_update"))) {
                switch($_GET['find_by']) {
                    case "id":
                        if(isset($_GET['find_v'])) $finded = $db->query("SELECT * FROM objects WHERE id=".$_GET['find_v']);
                        else $result = "undefined_find";
                        break;
                    case "name":
                        if(isset($_GET['find_v'])) $finded = $db->query("SELECT * FROM objects WHERE id=".$_GET['find_v']);
                        else $result = "undefined_find";
                        break;
                    case "type":
                        if(isset($_GET['find_v'])) $finded = $db->query("SELECT * FROM objects WHERE id=".$_GET['find_v']);
                        else $result = "undefined_find";
                        break;
                    case "recently_update":
                        $db->query("UPDATE objects SET data = '".$_GET['status']."' WHERE timestamp > ".(time()-60));
                        break;
                }
            } else {
                $result['error_message'] = "wrong_find_type";
            }
        } else {
            $result['error_message'] = "wrong_value";
        }
        break;

    // NOT API

    default:
        $result['error_message'] = "not_defined_action";
        break;
}