<?php 

require 'simple_html_dom.php';

$html = file_get_html('https://ca.eltiempo.es/calidad-aire/valencia');

$titulo = $html->find('div[class=m_header-group]', 0);

echo "<div class='col-12'>";
echo $titulo->find('h1[class=m_header-name]', 0);
echo "</div>";

?>
