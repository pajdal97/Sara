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

echo '<h3>Historie</h3>
<div class="divider"></div>';

echo '<div class="row">
    <div class="col s6">
    
        <h4>Historie otevření</h4>
        <div class="input-field col s12">';
$historyR = $db->query("SELECT * FROM history");
while($history = $historyR->fetch_assoc()) {
    echo '<div>'.$history['name'].'</div>';
}
echo '
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