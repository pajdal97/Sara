<?php
/**
 * Created by IntelliJ IDEA.
 * User: pajdal97
 * Date: 7.4.17
 * Time: 19:15
 */

include './include/header.php';

echo '<div class="container">';
echo '<div class="card-panel" style="height:600px;">';

echo '<div class="row">
    <div class="col s12">
    
        <h4>Historie otevření</h4>
        <div class="input-field col s12">
        <ul class="collection">';
$historyR = $db->query("SELECT * FROM history");
while($history = $historyR->fetch_assoc()) {
    echo '<li class="collection-item">[Door-'.$history['door_id'].'] <b class="left-align">'.date("Y-m-d H:i:s",$history['timestamp']).' </b><span class="right">'.$history['value'].'</span></li>';
}
echo '
    </ul>

  </div>
        
    </div>
</div>';

echo '</div>';

echo '</div>';

echo '<script>
$(document).ready(function() {
    $("select").material_select();
  });
</script>';