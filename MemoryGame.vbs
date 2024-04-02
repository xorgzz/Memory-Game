Dim objShell
Set objShell = WScript.CreateObject("WScript.Shell")

' Replace "your_command_here" with the command you want to run
objShell.Run "cmd /c javac -d ./out src/memo/*.java && cd out && java memo.Main", 0, True

Set objShell = Nothing
