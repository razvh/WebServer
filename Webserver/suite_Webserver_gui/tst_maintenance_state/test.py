# -*- coding: utf-8 -*-

import names


def main():
    startApplication("Webserver.jar")
    clickButton(waitForObject(names.vVS_web_server_stopped_Start_JButton, 486418))
    mouseClick(waitForObject(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox), 13, 12, 0, Button.Button1)
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_maintenance_JLabel).text, "maintenance")
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_127_0_0_1_JLabel).text, "127.0.0.1")
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Server_listening_on_port_JTextField).text, "9090")
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Server_listening_on_port_JTextField).enabled, False)
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Web_root_directory_JTextField).enabled, True)
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Maintenance_directory_JTextField).enabled, False)
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Stop_JButton).text, "Stop")
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Switch_to_maintenance_mode_JCheckBox).selected, True)
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Switch_to_maintenance_mode_JCheckBox).enabled, True)
