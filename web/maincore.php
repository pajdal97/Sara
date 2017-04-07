<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 14:30
 */
include './config/db.php';
//include './lang/en/global.php';
$db = new mysqli($config['db']['host'], $config['db']['user'], $config['db']['pass'],$config['db']['database']);

$iMember = false;
if(isset($_COOKIE['ssid'])) {
    $session_data = $db->query("SELECT * FROM sessions WHERE ssid='".$_COOKIE['ssid']."'")->fetch_array()[0];
    if($session_data['id'] == true) {
        $iMember = true;
    }
}
define("iMember",$iMember);