
<?php
	$command = "sudo -u pi ps axf | grep \" *python /home/pi/raspberry/main_move.py\" | grep -v grep | awk '{print $1}' | head -n 1";
	$pid = exec($command);
	$command = "sudo kill " . $pid;
	echo $command;
	exec($command);
?>

