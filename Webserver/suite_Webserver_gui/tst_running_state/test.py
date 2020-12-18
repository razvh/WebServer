# -*- coding: utf-8 -*-

import names


def main():
    startApplication("Webserver.jar")
    mouseClick(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), 30, 6, 0, Button.Button1)
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Backspace>")
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Backspace>")
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Backspace>")
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Backspace>")
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Numpad9>")
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Numpad0>")
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Numpad9>")
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Numpad1>")
    clickButton(waitForObject(names.vVS_web_server_stopped_Start_JButton))
    test.compare(waitForObjectExists(names.vVS_web_server_running_running_JLabel).text, "running..")
    test.compare(waitForObjectExists(names.vVS_web_server_running_127_0_0_1_JLabel).text, "127.0.0.1")
    test.compare(waitForObjectExists(names.vVS_web_server_running_9091_JLabel).text, "9091")
    test.compare(waitForObjectExists(names.vVS_web_server_running_Stop_JButton).text, "Stop")
    test.compare(waitForObjectExists(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox).enabled, True)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox).selected, False)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Server_listening_on_port_JTextField).enabled, False)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Web_root_directory_JTextField).enabled, False)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Web_root_directory_JTextField).text, "C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\")
    test.compare(waitForObjectExists(names.vVS_web_server_running_Maintenance_directory_JTextField).enabled, True)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Maintenance_directory_JTextField).text, "C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\maintenance\\")
    
