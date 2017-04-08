<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 15:33
 */
switch($_GET['type']) {

    case "door":
        $listValues = array("on", "off", "middle");
        break;

    case "light":
        $listValues = array("on", "off");
        break;
}
if(isset($listValues)) {
    if (in_array($_GET['status'], $listValues)) {
        if (in_array($_GET['find_by'], array("id", "name", "type", "recently_update","room"))) {
            switch ($_GET['find_by']) {
                case "id":
                    if (isset($_GET['find_v'])) {
                        $finded = $db->query("SELECT * FROM objects WHERE id=".$_GET['find_v']);
                    } else {
                        $result = "undefined_find";
                    }
                    break;
                case "name":
                    if (isset($_GET['find_v'])) $finded = $db->query("SELECT * FROM objects WHERE name='" . $_GET['find_v'] . "'");
                    else $result = "undefined_find";
                    break;
                case "type":
                    $finded = $db->query("SELECT * FROM objects WHERE type='".$_GET['type']."'");
                    break;
                case "room":
                    if (isset($_GET['find_v'])) $finded = $db->query("SELECT * FROM objects WHERE room='".$_GET['find_v']."'");
                    else $result = "undefined_find";
                    break;
                case "recently_update":
                    $finded = $db->query("SELECT * FROM objects WHERE timestamp > " . (time() - 60));
                    break;
            }
            while ($row = $finded->fetch_assoc()) {
                $db->query("UPDATE objects SET data='" . $_GET['status'] . "' WHERE id=".$row['id']);
            }
        } else {
            $result['error_message'] = "undefined_find_type";
        }
    } else {
        $result['error_message'] = "wrong_value";
    }
} else {
    $result['error_message'] = "undefined_type";
}