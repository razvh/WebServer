# -*- coding: utf-8 -*-

import names


def main():
    startApplication("Webserver.jar")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Start_JButton).text, "Start")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Switch_to_maintenance_mode_JCheckBox).text, "Switch to maintenance mode")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Switch_to_maintenance_mode_JCheckBox).enabled, False)
    clickButton(waitForObject(names.vVS_web_server_stopped_Start_JButton))
    test.compare(waitForObjectExists(names.vVS_web_server_running_Stop_JButton).text, "Stop")
    test.compare(waitForObjectExists(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox).selected, False)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox).enabled, True)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox).text, "Switch to maintenance mode")
    clickButton(waitForObject(names.vVS_web_server_running_Stop_JButton))
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Start_JButton).text, "Start")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Switch_to_maintenance_mode_JCheckBox).text, "Switch to maintenance mode")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Switch_to_maintenance_mode_JCheckBox).enabled, False)
    clickButton(waitForObject(names.vVS_web_server_stopped_Start_JButton))
    mouseClick(waitForObject(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox), 8, 11, 0, Button.Button1)
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Stop_JButton).text, "Stop")
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Switch_to_maintenance_mode_JCheckBox).text, "Switch to maintenance mode")
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Switch_to_maintenance_mode_JCheckBox).enabled, True)
    test.compare(waitForObjectExists(names.vVS_web_server_maintenance_Switch_to_maintenance_mode_JCheckBox).selected, True)
    mouseClick(waitForObject(names.vVS_web_server_maintenance_Switch_to_maintenance_mode_JCheckBox), 5, 14, 0, Button.Button1)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Stop_JButton).text, "Stop")
    test.compare(waitForObjectExists(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox).text, "Switch to maintenance mode")
    test.compare(waitForObjectExists(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox).selected, False)
    test.compare(waitForObjectExists(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox).enabled, True)
    mouseClick(waitForObject(names.vVS_web_server_running_Switch_to_maintenance_mode_JCheckBox), 14, 12, 0, Button.Button1)
    clickButton(waitForObject(names.vVS_web_server_maintenance_Stop_JButton))
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Start_JButton).text, "Start")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Switch_to_maintenance_mode_JCheckBox).text, "Switch to maintenance mode")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Switch_to_maintenance_mode_JCheckBox).selected, False)
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Switch_to_maintenance_mode_JCheckBox).enabled, False)
