def badinputs():
    a = <warning>input("Password: ")</warning>
    a = <warning>input("Please enter a password: ")</warning>
    a = <warning>input("please enter a password")</warning>
    a = <warning>input("enter password")</warning>
    a = <warning>input("password")</warning>
    a = <warning>input("Enter password:")</warning>

def goodinputs():
    a = input("Username:")
    a = input("Enter username:")
    a = input("How many passwords do you have? ")
    a = input("Enter data")

def imput(notprompt):
    return 1

def notinputs():
    a = len([1, 2, 3, 4])
    a = imput("I am not a prompt")

class DefinesInput:
    def input(self, fakeprompt):
        return 1

    def donthighlighthere(self):
        a = self.input("Password:")

def donthighlight():
    a = DefinesInput().input("Password:")
    input_definer = DefinesInput()
    a = input_definer.input("Password")