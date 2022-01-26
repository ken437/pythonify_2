def wedontclosefiles():
    """
    We don't close files!
    Let's see if the linter gets thrown off by this comment:
    f = open("I'm a file!", "w")
    f.write()
    # Oops, I forgot to close it!
    :return:
    """
    f = <warning>open("I'm a file!", "w")</warning>
    f.write("Content!")
    # Oops, I forgot to close it!

    f = <warning>open("I'm a file!", "r")</warning>
    print(f.read())
    # Oops, I forgot to close it!

    f = <warning>open("I'm a file!", "w")</warning>
    f.write("Content!")
    f.close()

    f = <warning>open("I'm a file!", "w")</warning>
    f.write("Content!")
    f.close()

    f = <warning>open("I'm a file!", "a")</warning>
    f.write("Content!")
    f.close()

    f=  <warning>open    ( "I'm a file!" ,"a" )</warning>
    f.write("Content!")
    f.close()

    # the correct way to use a file
    with open("I'm a file") as f:
        f.write("Content!")

    with open("I'm a file") as f:
        # Extraneous comment
        f.write("Content!")

class NotFileUser:
    def open(self, fake_filename, fake_filemode):
        print("I'm not opening a file!")
        return 1

    def dont_highlight_here(self):
        f = self.open("I'm not a file!", "I'm not a filemode!")
        print(f)

def donthighlight():
    nfs = NotFileUser()
    print(nfs.open("I'm not a file!", "I'm not a filename!"))

from PIL import Image
def pillow():
    im = Image.open("Image file", "mode")