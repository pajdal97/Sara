<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 19:52
 */
echo '<style>
.room {
    cursor:pointer;
    position:absolute;
    background:rgba(0,0,0,1);
    opacity:0.2;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.14), 0 1px 18px 0 rgba(0, 0, 0, 0.12), 0 3px 5px -1px rgba(0, 0, 0, 0.3);
    transition:all 0.3s;
}
.room:hover {
    box-shadow: 0 16px 24px 2px rgba(0, 0, 0, 0.14), 0 6px 30px 5px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(0, 0, 0, 0.3);
    opacity:0.5;
}
.light {
    position:absolute;
    width:30px;
    height:30px;
    border-radius:100%;
    transition:all .3s;
    background:gray;
    box-shadow: 0px 0px 5px 0px rgba(0,0,0,0.75);
}
.light[light="on"] {
    background:white;
    box-shadow: 0px 0px 80px 5px yellow;
}
</style>';
echo '<div id="house" style="position:relative;height:100px;">';

    $roomsR = $db->query("SELECT * FROM rooms");
    while($room = $roomsR->fetch_assoc()) {
        $objectsR = $db->query("SELECT * FROM objects WHERE room='".$room['id']."'");
        $attr_object = "";
        while($object = $objectsR->fetch_assoc()) {
            $attr_object .= " ".$object['type']."='".$object['data']."'";
        }
        echo '<div class="room" name="'.$room['name'].'" '.$attr_object.' style="background:'.$room['color'].';top:'.$room['top'].'px;left:'.$room['left'].'px;width:'.$room['width'].'px;height:'.$room['height'].'px;"></div>';
    }
echo '<script>
function reload_objects() {
    $.ajax({
        method: "POST",
        url: "some.php",
        data: { token:"easy_token",  }
    });

}
$(document).ready(function(){
    var position,width,height;
    var light = [];
    $(".room[light]").each(function( index ) {
       position = $(this).position();
       width = $(this).width();
       height = $(this).height();
       light.left = (position.left+((width/2))-15);
       light.top = (position.top+((height/2))-15);
       $("#house").append(\'<div class="light" light="\'+($(this).attr("light"))+\'" name="\'+($(this).attr("name"))+\'" style="top:\'+(light.top)+\'px;left:\'+(light.left)+\'px;"></div>\');
       
    });
});
</script>';

echo '</div>';
