#Read the Read.Me for instructions on use
#Created by Riley Moore, Jacob Barlett, and Sam Wilson

from operator import indexOf
import re

paths = []
userInputs = []
machineInputs = []
finalStates = []
machineStates = []

class stateMachine:

    def initializeFile(self,file):
        machineString = ''
        nestedStrings = set()
        listOfSentences = file.readlines()
        for elm in listOfSentences:
            machineString += elm

        for start in range(len(listOfSentences)):
            string = machineString[start:]
            nestedStrings.update(re.findall('\(.*?\)', string))

        stateMachine0 = stateMachine()
        stateMachine0.initializeMachine(nestedStrings)


    #get the user input for file name
    def userInput(self):

        listOfInputs = []

        #Get file input
        fileName = input("Please input the file name: ")
        openFile = open(fileName)
        self.initializeFile(openFile)

        userExit = True
        str = ""
        while(userExit):
            userString = input("Please input a string: ")
            if(userString == str):
                userExit = False
            else:
                listOfInputs.append(self.traverseMachine(userString))
                print(self.traverseMachine(userString))
        print("Bye bye.")
        print(listOfInputs)


    #elements of a state machine
    def __init__(self,paths,userInputs,machineInputs,machineStates,finalStates) -> None:
        self.paths = paths
        self.userInputs = userInputs
        self.machineInputs = machineInputs
        self.machineStates = machineStates
        self.finalStates = finalStates

    def setPaths(self,paths):
        self.paths = paths
    
    def __init__(self) -> None:
        pass
    
    #state machine info
    def printStateMachineInfo(self):
        initialState = 'q0'
        print('paths:',paths)
        print('userInputs:',userInputs)
        print('machineInputs:',machineInputs)
        print('machineStates:',machineStates)
        print('finalStates:',finalStates)
        print('initialState:', initialState)

    #removes unneeded chars from tuple
    def breakdownTuple(self,elm):
        elm = elm.replace('(','')
        elm = elm.replace(')','')
        elm = elm.replace(',','')
        return elm

    #WIP
    def fixList(self,unfixedList):
        fixedList = unfixedList[0].split()
        print(fixedList)

    def initializeMachine(self,nestedStrings):
        for elm in nestedStrings:
            elm = self.breakdownTuple(elm)
            splitElm = elm.split()

            #turns machine paths into a list
            if len(splitElm) == 3 and not splitElm[0].isdigit() and splitElm[1].isdigit() and not splitElm[2].isdigit():
                paths.append(splitElm)

            #turns user inputs into a list
            elif len(splitElm) == 3 and splitElm[0].isdigit() and splitElm[1].isdigit() and splitElm[2].isdigit():
                for var in splitElm:
                    userInputs.append(var)

            #turns machine inputs into a list
            elif len(splitElm) == 2:
                for var in splitElm:
                    
                    machineInputs.append(var)

            #turns final states into a list
            elif len(splitElm) == 1:
                finalStates.append(elm)

            #turns machine states into a list
            elif len(splitElm) == 3 and not splitElm[0].isdigit() and not splitElm[1].isdigit() and not splitElm[2].isdigit():
                for var in splitElm:
                    machineStates.append(var)

    def completedPath(self,state):
        if state in finalStates:
            return "accepted"
        return "rejected"

    #Finds best path 
    def traverseMachine(self,userInput):
        currState = 'q0'
        possiblePaths = []

        for elm in userInput:

            for innerList in paths:
                for item in innerList:
                    if elm == item:
                        possiblePaths.append(innerList)
            
            for i in range(len(possiblePaths)):
                if elm == possiblePaths[i][1] and currState == possiblePaths[i][0]:
                    currState = possiblePaths[i][2]    
                    
            
            possiblePaths.clear()
        return self.completedPath(currState)
        
stateMachine1 = stateMachine()
stateMachine1.userInput()


