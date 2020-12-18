# -*- coding: utf-8 -*-

import names


def main():
    startApplication("Webserver.jar")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_not_running_JLabel).text, "not running")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_not_running_JLabel_3).text, "not running")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_not_running_JLabel_2).text, "not running")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Start_JButton).text, "Start")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Server_listening_on_port_JTextField).text, "9090")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Web_root_directory_JTextField).text, "C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Maintenance_directory_JTextField).text, "C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\maintenance\\")
    test.vp("VP1")
    test.vp("VP2")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Switch_to_maintenance_mode_JCheckBox).enabled, False)
