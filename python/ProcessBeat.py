import random


beatFile = open("BestDayBeatsManual.txt", 'r')
outFile = open("output.txt", 'w')

SINGLE_TAP = 0
MULTIPLE_TAP = 1
HOLD = 2
DRAG = 3

ON_DURATION = 2000 

beatPatternArray = "bestDayBeatPattern"
screenWidth = 1794
screenHeight = 1080
bpm = 120
beatDuration = 500 #milliseconds

currentTime = 0
currentBeatWindow = 0
beatWindow = 500
beatsInBeatWindow = []

totalBoundary = 140 #Number of Pixels each Circle needs to be separated from each other

LEFT_TO_RIGHT = 0
RIGHT_TO_LEFT = 1
TOP_TO_BOTTOM = 2
BOTTOM_TOP_TOP = 3

pathX = 0
pathY = 0

currentPath = LEFT_TO_RIGHT

currentOnWindow = 0
onWindow = ON_DURATION
beatsInPlay = []

class BeatCircle:
    
    def __init__(self, xLocation, yLocation, time):
        self.x = xLocation
        self.y = yLocation
        self.time = time

        self.boxLeft = xLocation - totalBoundary
        self.boxRight = xLocation + totalBoundary
        self.boxTop = yLocation - totalBoundary
        self.boxBottom = yLocation + totalBoundary

        #Single Tap (Default)
        self.circleType = SINGLE_TAP

        #Multiple Tap
        self.tapInterval = [0]
        self.tapCount = 1

    def relocate(self, xLocation, yLocation):
        self.x = xLocation
        self.y = yLocation

        self.boxLeft = xLocation - totalBoundary
        self.boxRight = xLocation + totalBoundary
        self.boxTop = yLocation - totalBoundary
        self.boxBottom = yLocation + totalBoundary

    def setType(self, beatCircleType):
        self.circleType = beatCircleType
        return

    def getType():
        return self.circleType

    def overlap(self, otherBeatCircle):
        #Overlap in Width
        return (self.overlapWidth(otherBeatCircle) and self.overlapHeight(otherBeatCircle))

    def overlapWidth(self, otherBeatCircle):
        if (self.boxLeft >= otherBeatCircle.boxLeft and self.boxLeft <= otherBeatCircle.boxRight):
            return True
        elif (self.boxRight >= otherBeatCircle.boxLeft and self.boxRight <= otherBeatCircle.boxRight):
            return True
        else:
            return False

    def overlapHeight(self, otherBeatCircle):
        if (self.boxTop >= otherBeatCircle.boxTop and self.boxTop <= otherBeatCircle.boxBottom):
            return True
        elif (self.boxBottom >= otherBeatCircle.boxTop and self.boxBottom <= otherBeatCircle.boxBottom):
            return True
        else:
            return False


def main():
    global currentTime, beatWindow, currentBeatWindow, beatsInPlay, beatsInBeatWindow
    global offset
    global currentOnWindow, onWindow
    global currentPath, pathX, pathY

    offset = 0
    foundOffset = False

    for line in beatFile:
        spaceDelimiter = line.split()
        extractedTime = spaceDelimiter[0]


        timeInMiliseconds = float(extractedTime)*1000
        actualTimeToAppear = timeInMiliseconds - ON_DURATION


        if (actualTimeToAppear < 0): 
            continue
        else:
            if ( not( foundOffset) ):
                foundOffset = True
                offset = currentTime
                previousTimeToAppear = offset

        currentTime = timeInMiliseconds-offset;


        if ( currentTime > currentOnWindow*onWindow):
            currentOnWindow += 1


        elif ( currentTime-offset > currentBeatWindow*beatWindow):
            currentBeatWindow += 1

            overlap = True
    
            suggestedBeatCircle = BeatCircle(0, 0, previousTimeToAppear)

            #print beatsInPlay
            print beatsInBeatWindow

            for beatTime in beatsInBeatWindow:
                difference = beatTime - previousTimeToAppear
                print difference
                suggestedBeatCircle.setType(MULTIPLE_TAP)
                suggestedBeatCircle.tapInterval.append(difference)
                suggestedBeatCircle.tapCount += 1

            while(overlap):

                if (currentPath == LEFT_TO_RIGHT):
                    pathX += totalBoundary
                    if (pathX >= screenWidth):
                        pathX = totalBoundary
                    x = pathX
                    y = random.randint(totalBoundary, 1080-totalBoundary) 
                else:
                    x = random.randint(totalBoundary, 1794-totalBoundary)
                    y = random.randint(totalBoundary, 1080-totalBoundary)

         
                suggestedBeatCircle.relocate(x, y)

                overlap = False
                for otherBeatCircle in beatsInPlay:
                    if suggestedBeatCircle.overlap(otherBeatCircle):
                        overlap = True
                        break;

            beatsInPlay.append(suggestedBeatCircle)
            beatsInBeatWindow = []
            previousTimeToAppear = actualTimeToAppear

        else:
            beatsInBeatWindow.append(actualTimeToAppear)

        for beatCircle in beatsInPlay:
            if (timeInMiliseconds > (beatCircle.time+ON_DURATION*2)):
                writeBeat(beatCircle)
                beatsInPlay.remove(beatCircle)

    #Write last few beats
    for beatCircle in beatsInPlay:
        writeBeat(beatCircle)
    
    beatFile.close()

def writeBeat(beatCircle):
    string = beatPatternArray + ".add( new Beat("
    beatType = beatCircle.circleType
    beatArguments = ""

    if (beatType == SINGLE_TAP):
        beatArguments = "BeatType.SingleTap, new String[] {" + '"' + str(beatCircle.x) + '"' + ", " + '"' + str(beatCircle.y) + '"' + "}, " + str(beatCircle.time) + "f));"
        #print string+beatArguments
    elif (beatType == MULTIPLE_TAP):
        stringArray = ""
        for index in range(0, beatCircle.tapCount-1):
            stringArray += str(beatCircle.tapInterval[index])
            stringArray += ","
        #Handle Last Float
        stringArray += str(beatCircle.tapInterval[beatCircle.tapCount-1])

        beatArguments = "BeatType.MultipleTap, new String[] {" + '"' + str(beatCircle.x) + '"' + ', "' + str(beatCircle.y) + '"' + ', "' + stringArray + '"' + ', "' + str(beatCircle.tapCount) + '"' + "}, " + str(beatCircle.time) + "f));"

    outFile.write(string+beatArguments+"\n")



if __name__ == '__main__':
   main()