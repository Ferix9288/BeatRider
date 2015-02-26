import random


beatFile = open("BestDayBeatsManual2.txt", 'r')
outFile = open("output.txt", 'w')

SINGLE_TAP = 0
MULTIPLE_TAP = 1
HOLD = 2
DRAG = 3
DOUBLE = 4

ON_DURATION = 1500

beatPatternArray = "bestDayBeatPattern"
screenWidth = 1794
screenHeight = 1080
bpm = 120
beatDuration = 500 #milliseconds

currentTime = 0
currentBeatWindow = 0
beatWindow = 500
beatsInBeatWindow = []

circleRadius = 120
labelBoundary = 50
widthBoundary = circleRadius #Number of Pixels each Circle needs to be separated from each other (from the center)
topBoundary = circleRadius + labelBoundary
bottomBoundary = circleRadius


LEFT_TO_RIGHT_TOP_HALF = 0
LEFT_TO_RIGHT_BOTTOM_HALF = 1
RIGHT_TO_LEFT_TOP_HALF = 2
RIGHT_TO_LEFT_BOTTOM_HALF = 3
DIAGONAL_DOWNLEFT_UPRIGHT = 4
DIAGONAL_UPLEFT_DOWNRIGHT = 5
DIAGONAL_DOWNRIGHT_UPLEFT = 6
DIAGONAL_UPRIGHT_DOWNLEFT = 7
ALTERNATING_LEFT_RIGHT_TOP_TO_BOTTOM = 8
ALTERNATIVE_LEFT_RIGHT_BOTTOM_TO_TOP = 9


pathX = widthBoundary
pathY = topBoundary

currentPath = LEFT_TO_RIGHT_TOP_HALF

currentOnWindow = 0
onWindow = ON_DURATION
beatsInPlay = []

class BeatCircle:
    
    def __init__(self, xLocation, yLocation, time):
        self.x = xLocation
        self.y = yLocation
        self.time = time

        self.boxLeft = xLocation - widthBoundary
        self.boxRight = xLocation + widthBoundary
        self.boxTop = yLocation - topBoundary
        self.boxBottom = yLocation + bottomBoundary

        #Single Tap (Default)
        self.circleType = SINGLE_TAP

        #Multiple Tap
        self.tapInterval = [0]
        self.tapCount = 1

        #Hold Circle
        self.holdDuration = beatWindow

        #Drag Circle
        self.dragPoints = []
        self.dragTimes = []

        #Drag Circle Path
        self.dragCirclePath = False

    def relocate(self, xLocation, yLocation):
        self.x = xLocation
        self.y = yLocation

        self.boxLeft = xLocation - widthBoundary
        self.boxRight = xLocation + widthBoundary
        self.boxTop = yLocation - topBoundary
        self.boxBottom = yLocation + bottomBoundary

    def setType(self, beatCircleType):
        self.circleType = beatCircleType
        return

    def getType():
        return self.circleType

    def overlap(self, otherBeatCircle):
        #Overlap in Width
        return (self.overlapWidth(otherBeatCircle) and self.overlapHeight(otherBeatCircle))

    def overlapWidth(self, otherBeatCircle):
        if (self.boxLeft > otherBeatCircle.boxLeft and self.boxLeft < otherBeatCircle.boxRight):
            return True
        elif (self.boxRight > otherBeatCircle.boxLeft and self.boxRight < otherBeatCircle.boxRight):
            return True
        else:
            return False

    def overlapHeight(self, otherBeatCircle):
        if (self.boxTop > otherBeatCircle.boxTop and self.boxTop < otherBeatCircle.boxBottom):
            return True
        elif (self.boxBottom > otherBeatCircle.boxTop and self.boxBottom < otherBeatCircle.boxBottom):
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
    foundFirstBeat = False
    manualCircleLabel = SINGLE_TAP
    previousManualCircleLabel = SINGLE_TAP
    for line in beatFile:
        spaceDelimiter = line.split()
        extractedTime = spaceDelimiter[0]

        if (len(spaceDelimiter) >= 3):
            manualLabelString = spaceDelimiter[2]
            print manualLabelString
            if (manualLabelString == "H"):
                manualCircleLabel = HOLD
            elif (manualLabelString == "D"):
                manualCircleLabel = DRAG
            elif (manualLabelString == "DB"):
                manualCircleLabel = DOUBLE

            print "manualCircleLabel:" + str(manualCircleLabel)
        else:
            manualCircleLabel = SINGLE_TAP

        timeInMiliseconds = float(extractedTime)*1000
        actualTimeToAppear = timeInMiliseconds - ON_DURATION

        if ( not( foundOffset) ):
            foundOffset = True
            offset = timeInMiliseconds
            print "offset: " + str(offset)

        if (actualTimeToAppear < 0): 
            print actualTimeToAppear
            continue
        else:
            if (not (foundFirstBeat)):
                previousTimeToAppear = actualTimeToAppear
                previousManualCircleLabel = manualCircleLabel
                foundFirstBeat = True



        currentTime = timeInMiliseconds-offset;


        # if ( currentTime > currentOnWindow*onWindow):
        #     currentOnWindow += 1


        if ( currentTime > currentBeatWindow*beatWindow):
            currentBeatWindow += 1
    
            suggestedBeatCircle = BeatCircle(0, 0, previousTimeToAppear)
            doubleBeatCircle = BeatCircle(0, 0, previousTimeToAppear)

            # print "Current Line: " + line
            # print "previousTimeToAppear: " + str(previousTimeToAppear)
            # print "previousLabel: " + str(previousManualCircleLabel)


            if (previousManualCircleLabel != SINGLE_TAP):
                if (previousManualCircleLabel == HOLD):
                    # print "Set Circle Hold."
                    suggestedBeatCircle.setType(HOLD)
                    suggestedBeatCircle.holdDuration = actualTimeToAppear-previousTimeToAppear
                elif (previousManualCircleLabel == DRAG):
                    empty = 0

                elif (previousManualCircleLabel == DOUBLE):
                    # print "Set Circle Double."
                    empty = 0

            elif ( len(beatsInBeatWindow) > 0):
                suggestedBeatCircle.setType(MULTIPLE_TAP)    
                for beatTime in beatsInBeatWindow:
                    difference = beatTime - previousTimeToAppear
                    suggestedBeatCircle.tapInterval.append(difference)
                    suggestedBeatCircle.tapCount += 1
     
           

                # timeLapse = actualTimeToAppear-previousTimeToAppear
                # if (timeLapse > beatWindow*1.5):
                #     suggestedBeatCircle.setType(DRAG)
                #     suggestedBeatCircle.dragDuration = timeLapse


            overlap = True
            while(overlap):

                if (currentPath == LEFT_TO_RIGHT_TOP_HALF or currentPath == RIGHT_TO_LEFT_TOP_HALF):
                    x = pathX
                    y = random.randint(topBoundary, screenHeight/2)
                elif (currentPath == LEFT_TO_RIGHT_BOTTOM_HALF or currentPath == RIGHT_TO_LEFT_BOTTOM_HALF):
                    x = pathX
                    y = random.randint(screenHeight/2, screenHeight-bottomBoundary) 
                else:
                    x = random.randint(widthBoundary, screenWidth-widthBoundary)
                    y = random.randint(topBoundary, screenHeight-bottomBoundary)
         
                suggestedBeatCircle.relocate(x, y)
                overlap = False
                for otherBeatCircle in beatsInPlay:
                    if suggestedBeatCircle.overlap(otherBeatCircle):
                        overlap = True
                        break;

                if (previousManualCircleLabel == DOUBLE):
                    sign = random.random()
                    if (sign < .5):
                        sign = -1
                    else:
                        sign = 1
                    doubleY = random.randint(topBoundary, topBoundary*2) 
                    newY = y+(doubleY*sign) 
                    if not(newY in range(topBoundary, screenHeight - bottomBoundary)):
                        overlap = True
                        break
                    doubleBeatCircle.relocate(x, newY)
                    if (doubleBeatCircle.overlap(suggestedBeatCircle)):
                        overlap = True
                        break 
                    else: 
                       for otherBeatCircle in beatsInPlay:
                            if doubleBeatCircle.overlap(otherBeatCircle):
                                overlap = True
                                break

            #On success
            incrementPath()
                
            beatsInPlay.append(suggestedBeatCircle)
            if (previousManualCircleLabel == DOUBLE):
                beatsInPlay.append(doubleBeatCircle)
            beatsInBeatWindow = []
            previousTimeToAppear = actualTimeToAppear
            previousManualCircleLabel = manualCircleLabel;

        else:
            beatsInBeatWindow.append(actualTimeToAppear)

        print beatsInPlay
        for beatCircle in beatsInPlay:
            if (timeInMiliseconds > (beatCircle.time+ON_DURATION*2)):
                writeBeat(beatCircle)
                beatsInPlay.remove(beatCircle)

    #Write last few beats
    for beatCircle in beatsInPlay:
        writeBeat(beatCircle)
    
    beatFile.close()

def incrementPath():
    global currentPath, pathX, pathY
    if (currentPath == LEFT_TO_RIGHT_TOP_HALF or currentPath == LEFT_TO_RIGHT_BOTTOM_HALF):
        pathX += widthBoundary*2;
        if (pathX > screenWidth-widthBoundary):
            switchPath()
    elif (currentPath == RIGHT_TO_LEFT_TOP_HALF or currentPath == RIGHT_TO_LEFT_BOTTOM_HALF):
        pathX -= widthBoundary*2;
        if (pathX < widthBoundary):
            switchPath()

def switchPath():
    global currentPath

    if (currentPath == LEFT_TO_RIGHT_TOP_HALF): 
        choices =  [LEFT_TO_RIGHT_BOTTOM_HALF, RIGHT_TO_LEFT_BOTTOM_HALF]
        currentPath = random.choice( choices)

    elif (currentPath == LEFT_TO_RIGHT_BOTTOM_HALF):
        choices =  [LEFT_TO_RIGHT_TOP_HALF, RIGHT_TO_LEFT_TOP_HALF]
        currentPath = random.choice( choices)

    elif (currentPath == RIGHT_TO_LEFT_TOP_HALF):
        choices =  [LEFT_TO_RIGHT_BOTTOM_HALF, RIGHT_TO_LEFT_BOTTOM_HALF]
        currentPath = random.choice( choices)

    elif (currentPath == RIGHT_TO_LEFT_BOTTOM_HALF):
        choices =  [LEFT_TO_RIGHT_TOP_HALF, RIGHT_TO_LEFT_TOP_HALF]
        currentPath = random.choice( choices)

    setUpPath()

def setUpPath():
    global currentPath
    global pathX, pathY
    if (currentPath == LEFT_TO_RIGHT_TOP_HALF or currentPath == LEFT_TO_RIGHT_BOTTOM_HALF): 
        pathX = widthBoundary
    elif (currentPath == RIGHT_TO_LEFT_TOP_HALF or currentPath == RIGHT_TO_LEFT_BOTTOM_HALF):
        pathX = screenWidth - widthBoundary





def writeBeat(beatCircle):
    string = beatPatternArray + ".add( new Beat("
    beatType = beatCircle.circleType
    beatArguments = ""

    print "In Write Beat: BeatCircle - " + str(beatType)

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

    elif (beatType == HOLD): 
        beatArguments = "BeatType.Hold, new String[] {" + '"' + str(beatCircle.x) + '"' + ', "' + str(beatCircle.y) + '"' + ', "' + str(beatCircle.holdDuration) + '"' + "}, " + str(beatCircle.time) + "f));"
        
    elif (beatType == DRAG):
        beatArguments = "BeatType.Drag, new String[] {" + '"' + str(beatCircle.x) + '"' + ", " + '"' + str(beatCircle.y) + '"' +', "' + str(beatCircle.dragDuration) + '"' + "}, " + str(beatCircle.time) + "f));"
   

    outFile.write(string+beatArguments+"\n")



if __name__ == '__main__':
   main()