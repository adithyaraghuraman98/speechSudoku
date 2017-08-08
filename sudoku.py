#Adithya Raghuraman + Sudoku with Speech Recognition

from tkinter import *
import copy
import speech_recognition as sr
import time

def representsInt(s):
    try: 
        int(s)
        return True
    except ValueError:
        return False

def getBoard(data):
    r = sr.Recognizer()
    with sr.Microphone() as source:
        print("say something")
        audio = r.listen(source)
    preResult = r.recognize_google(audio).split(" ")
    num = data.num
    numWord = data.numWord
    for i in range(len(preResult)):
        if(preResult[i] in numWord):preResult[i] = num[numWord.index(preResult[i])]
    result = []
    for elem in preResult:
        if(len(elem)==0): continue
        if(len(elem)==1): result.append(elem)
        else:
            for number in list(elem): 
                result.append(number)

    result[:] = [s for s in result if representsInt(s)]
    result = list(map(int, result))    
    out = data.board
    if(data.first): sub = 0
    elif(data.second): sub = 3
    else: 
        sub = 6 
    for i in range(sub,sub+3):
        for j in range(len(out[0])):
            if(9*(i-sub)+j< len(result)):
                out[i][j] = result[9*(i-sub)+j]
    return out

def conv(s,data):
    preResult = s.split(" ")
    num = data.num
    numWord = data.numWord
    for i in range(len(preResult)):
        if(preResult[i] in numWord):preResult[i] = num[numWord.index(preResult[i])]
    return preResult[0]

def findInd(s,data):
    return (data.letters.index(s[0].lower()),int(s[1]))

def getMove(data):
    def callback(recognizer, audio):
        try:
            s = recognizer.recognize_google(audio)
            print("Google Speech Recognition thinks you said " + s)
            if(s=="solution"):
                solve(0,0,data.board,data)
            else:
                split = s.split(" ")

                if(split[0].lower()=="put"):
                    val = int(conv(split[1],data))
                    (row,col) = findInd(split[3],data)
                    auxboard = copy.deepcopy(data.board)
                    auxboard[row][col] = val
                    if(isLegalBoard(auxboard)):
                        data.board[row][col] = val
                        data.selection = (row,col)
                    else:
                        data.invalidNumFlag = True
                        data.counter = 0
                elif(split[0].lower()=="display"):
                    (row,col) = findInd(split[1],data)
                    data.selection = (row,col)
            data.numDict = generateDict(data.board)
        except sr.UnknownValueError:
            print("Google Speech Recognition could not understand audio")
        except sr.RequestError as e:
            print("Could not request results from Google Speech Recognition service; {0}".format(e))

    r = sr.Recognizer()
    m = sr.Microphone()
    with m as source:
        r.adjust_for_ambient_noise(source)

    stop_listening = r.listen_in_background(m, callback)
    for _ in range(70): time.sleep(0.1)  
    stop_listening()
    data.ingame = False
    return None
    while True: time.sleep(0.1)

def init(data):
    data.rows = 9
    data.cols = 9
    data.boardSize = 9
    data.margin = 65 # margin around grid
    data.selection = (-1, -1) # (row, col) of selection, (-1,-1) for none
    data.displayMain = True
    data.displaySpeech = False
    data.setBoard = False
    data.first = True
    data.second = False
    data.third = False
    data.ingame = False
    data.gameReg = 0
    data.firstgame = False
    data.letters = ['a','b','c','d','e','f','g','h','i']
    data.board = [
    [0,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,0],
    ]
    data.num = ['0','1','2','3','4','5','6','7','8','9']
    data.numWord = ['zero','one','two','three','four','five','six','seven','eight',
                'nine']
    data.numDict = generateDict(data.board)
    data.invalidKeyFlag = False
    data.invalidNumFlag = False
    data.counter = 0
    data.side = 0
    data.moveStack = []

def generateDict(board):
    out = dict()
    for row in range(len(board)):
        for col in range(len(board[0])):
            if(board[row][col]!=0):
                if(board[row][col] not in out):out[board[row][col]]=list()
                out[board[row][col]].append((row,col))
    return out

def pointInGrid(x, y, data):
    # return True if (x, y) is inside the grid defined by data.
    return ((data.margin <= x <= data.width-data.margin) and
            (data.margin <= y <= data.height-data.margin))

def getCell(x, y, data):
    # return (row, col) in which (x, y) occurred or (-1, -1) if outside grid.
    if (not pointInGrid(x, y, data)):
        return (-1, -1)
    gridWidth  = data.width - 2*data.margin
    gridHeight = data.height - 2*data.margin
    cellWidth  = gridWidth / data.cols
    cellHeight = gridHeight / data.rows
    row = (y - data.margin) // cellHeight
    col = (x - data.margin) // cellWidth
    # triple-check that we are in bounds
    row = min(data.rows-1, max(0, row))
    col = min(data.cols-1, max(0, col))
    return (int(row), int(col))

def getCellBounds(row, col, data):
    # returns (x0, y0, x1, y1) corners/bounding box of given cell in grid
    gridWidth  = data.width - 2*data.margin
    gridHeight = data.height - 2*data.margin
    columnWidth = gridWidth / data.cols
    rowHeight = gridHeight / data.rows
    x0 = data.margin + col * columnWidth
    x1 = data.margin + (col+1) * columnWidth
    y0 = data.margin + row * rowHeight
    y1 = data.margin + (row+1) * rowHeight
    return (x0, y0, x1, y1)

def mousePressed(event, data):
    if(not data.invalidKeyFlag and not data.invalidNumFlag):
        (row, col) = getCell(event.x, event.y, data)
        # select this (row, col) unless it is selected
        #data.numDict = generateDict(data.board)
        if (data.selection == (row, col)):
            data.selection = (-1, -1)
        else:
            data.selection = (row, col)

def isValidKey(key):
    if(len(key)>1):return False
    if(ord(key) not in range(49,58)):return False
    return True

def isValidNum(row,col,num,data):
    auxboard = copy.deepcopy(data.board)
    auxboard[row][col] = num
    return isLegalBoard(auxboard)

def solve(i, j, board,data):
    if(i==len(board)):return True
    if (board[i][j] != 0):
        return solve(i+((j+1)//data.boardSize),(j+1)%data.boardSize,
            board,data)
    for val in range(1,10): 
        board[i][j] = val
        if (isLegalBoard(board)): 
            if (solve(i+((j+1)//data.boardSize),(j+1)%data.boardSize,
                board,data)):
                return True
        board[i][j] = 0
    return False
    
def isLegalBoard(board):
    for row in range(len(board)):
        if(not isLegalRow(board, row)):return False
    for col in range(len(board)):
        if(not isLegalCol(board, col)):return False
    for block in range(len(board)):
        if(not isLegalBlock(board, block)):return False
    return True

def isLegalRow(board, row):
    vals = set()
    for number in board[row]:
        if(number!=0):
            if(number in vals): return False
            vals.add(number)    
    return True    

def isLegalCol(board,col):
    vals = set()
    for i in range(len(board)):
        if(board[i][col]!=0):
            if(board[i][col] in vals):return False
            vals.add(board[i][col])
    return True

def isLegalBlock(board, block):
    blockSize = 3
    vals = set()
    rowstart = blockSize*(block//blockSize)
    colstart = blockSize*(block%blockSize)
    for i in range (rowstart,rowstart+blockSize):
        for j in range(colstart,colstart+blockSize):
            if(board[i][j]!=0):
                if(board[i][j] in vals):return False
                vals.add(board[i][j])
    return True

def keyPressed(event, data):
    if(data.displayMain==True):
        if(event.keysym=="Return"):
            data.displayMain = False
            data.displaySpeech = True
    else:
        if(event.keysym=="s"):
            solve(0,0,data.board,data)
            data.numDict = generateDict(data.board)
        elif(not data.invalidKeyFlag and not data.invalidNumFlag):
            (row,col) = data.selection
            if(isValidKey(event.keysym)):
                if((row,col)!=(-1,-1) and data.board[row][col]==0):
                    if(isValidNum(row,col,int(event.keysym),data)):
                        data.board[row][col] = int(event.keysym)
                        data.numDict = generateDict(data.board)
                    else:
                        data.invalidNumFlag  = True
                        data.counter = 0
            else:
                data.invalidKeyFlag = True
                data.counter = 0
def timerFired(data):
    if(data.invalidKeyFlag):
        data.counter+=1
    if(data.invalidNumFlag):
        data.counter+=1
    if(data.displaySpeech):
        if(data.first):
            data.counter+=1
            if(data.counter>5):
                data.setBoard = True
                data.counter = 0
        if(data.second):
            data.counter+=1
            if(data.counter>5):
                data.setBoard = True 
                data.counter = 0
        if(data.third):
            data.counter+=1
            if(data.counter>5):
                data.setBoard = True 
                data.counter = 0
    if(data.counter>20):    
        data.invalidKeyFlag = False
        data.invalidNumFlag = False
        data.counter = 0
    if(not data.ingame and data.firstgame):
        data.gameReg+=1
    if(data.gameReg%50==1):
        getMove(data)

def redrawAll(canvas, data):
    if(data.displayMain):
        canvas.create_text(data.width/2,data.height/2,
            text="""Welcome to Sukoku Speech Recognition.
            Press "Return" to continue""")
    elif(data.displaySpeech):
            if(data.first):
                canvas.create_text(data.width/2,data.height/2,
                    text="Input First 3 rows...")
                if(data.setBoard):
                    data.board = getBoard(data)
                    data.first = False
                    data.second = True
                    data.setBoard = False
            elif(data.second):
                canvas.create_text(data.width/2,data.height/2,
                    text="Input rows 4-6...")
                if(data.setBoard):
                    data.board = getBoard(data)
                    data.second = False
                    data.third = True
                    data.setBoard = False
            elif(data.third):
                canvas.create_text(data.width/2,data.height/2,
                    text="Input rows 7-9...")
                if(data.setBoard):
                    data.board = getBoard(data)
                    data.third = False
                    data.setBoard = False
                    data.displaySpeech = False
                data.numDict = generateDict(data.board)
    else:
        gridWidth  = data.width - 2*data.margin
        gridHeight = data.height - 2*data.margin
        cellWidth  = gridWidth / data.cols
        cellHeight = gridHeight / data.rows
        for row in range(data.rows):
            canvas.create_text(data.margin+(cellHeight/2)*(2*row+1),
                data.height-data.margin/2, text = data.num[row])
            canvas.create_text(data.margin/2,
                data.margin+(cellHeight/2)*(2*row+1),text = data.letters[row])
            for col in range(data.cols):
                (x0, y0, x1, y1) = getCellBounds(row, col, data)
                if(data.side == 0):data.side = x1-x0
                canvas.create_rectangle(x0, y0, x1, y1)
                if(row%3==0 and col%3==0):
                    canvas.create_rectangle(x0, y0, x0+3*data.side, 
                        y0+3*data.side, width = 3)
                if(data.board[row][col]!=0):
                    canvas.create_text((x0+x1)/2,(y0+y1)/2,
                        text = data.board[row][col])
        canvas.create_text(data.width/2,data.margin/2,
                        text = "To solve the puzzle, say 'solution'")
        print(data.gameReg)
        if(data.selection!=(-1,-1)):
            (row,col) = data.selection
            (x0, y0, x1, y1) = getCellBounds(row, col, data)
            if(data.board[row][col]==0):
                canvas.create_rectangle(x0, y0, x1, y1, outline="red", 
                    width = 3)
            else:
                val = data.board[row][col]
                for (row,col) in data.numDict[val]:
                    (x0, y0, x1, y1) = getCellBounds(row, col, data)
                    canvas.create_rectangle(x0, y0, x1, y1, outline="#057115",
                        width = 3) 
        if(data.invalidKeyFlag):
            canvas.create_text(data.width/2,data.height/2,text="Invalid Key")
        if(data.invalidNumFlag):
            canvas.create_text(data.width/2,data.height/2,
                text="Invalid Number")
        if(not data.firstgame):
            data.ingame = True
            data.firstgame = True
            data.gameReg+=1


def run(width=500, height=500):
    def redrawAllWrapper(canvas, data):
        canvas.delete(ALL)
        redrawAll(canvas, data)
        canvas.update()    

    def mousePressedWrapper(event, canvas, data):
        mousePressed(event, data)
        redrawAllWrapper(canvas, data)

    def keyPressedWrapper(event, canvas, data):
        keyPressed(event, data)
        redrawAllWrapper(canvas, data)

    def timerFiredWrapper(canvas, data):
        timerFired(data)
        redrawAllWrapper(canvas, data)
        # pause, then call timerFired again
        canvas.after(data.timerDelay, timerFiredWrapper, canvas, data)
    # Set up data and call init
    class Struct(object): pass
    data = Struct()
    data.width = width
    data.height = height
    data.timerDelay = 100 # milliseconds
    init(data)
    # create the root and the canvas
    root = Tk()
    canvas = Canvas(root, width=data.width, height=data.height)
    canvas.pack()
    # set up events
    root.bind("<Button-1>", lambda event:
                            mousePressedWrapper(event, canvas, data))
    root.bind("<Key>", lambda event:
                            keyPressedWrapper(event, canvas, data))
    timerFiredWrapper(canvas, data)
    # and launch the app
    root.mainloop()  # blocks until window is closed

run()
