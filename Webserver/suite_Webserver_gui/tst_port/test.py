# -*- coding: utf-8 -*-

import names


def main():
    startApplication("Webserver.jar")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Server_listening_on_port_JTextField).text, "9090")
    clickButton(waitForObject(names.vVS_web_server_stopped_Start_JButton))
    test.compare(waitForObjectExists(names.vVS_web_server_running_9090_JLabel).text, "9090")
    clickButton(waitForObject(names.vVS_web_server_running_Stop_JButton))
    mouseClick(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), 35, 14, 0, Button.Button1)
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Backspace>")
    type(waitForObject(names.vVS_web_server_stopped_Server_listening_on_port_JTextField), "<Numpad1>")
    test.compare(waitForObjectExists(names.vVS_web_server_stopped_Server_listening_on_port_JTextField).text, "9091")
    clickButton(waitForObject(names.vVS_web_server_stopped_Start_JButton))
    test.compare(waitForObjectExists(names.vVS_web_server_running_9091_JLabel).text, "9091")
