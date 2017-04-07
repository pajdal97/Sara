<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 15:33
 */

switch($_GET['type']) {

    case "door":
        $listValues = array("open","close","middle");
        break;

    case "light":
        $listValues = array("on","off");
        break;
}
if(isset($listValues)) {
        if (in_array($_GET['find_by'], array("id", "name", "type", "recently_update"))) {
            switch ($_GET['find_by']) {
                case "id":
                    if (isset($_GET['find_v'])) $finded = $db->query("SELECT * FROM objects WHERE id=" . $_GET['find_v']);
                    else $result = "undefined_find";
                    break;
                case "name":
                    if (isset($_GET['find_v'])) $finded = $db->query("SELECT * FROM objects WHERE name='" . $_GET['find_v'] . "'");
                    else $result = "undefined_find";
                    break;
                case "type":
                    $finded = $db->query("SELECT * FROM objects WHERE type='".$_GET['type']."'");
                    break;
                case "recently_update":
                    $finded = $db->query("SELECT * FROM objects WHERE timestamp > " . (time() - 60));
                    break;
            }
            while ($row = $finded->fetch_assoc()) {
                $result['data'][] = $row;
            }
        } else {
            $result['error_message'] = "undefined_find_type";
        }
} else {
    $result['error_message'] = "undefined_type";
}