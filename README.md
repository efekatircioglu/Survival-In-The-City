# Survival-In-The-City
my second coursework for my programming module.


The name of my game is “Survival in the City”. The game is quite simple. You spawn in a
tent and your goal is to go to Shelter. To go to Shelter, you need to use the car in the car
park. To use the car, you need two essential items: Gasoline and car engine. These items
cannot be picked up from the ground like other items in the game, but you can get these
items by picking up other items in the game and trading them to get gasoline and car
engine.
In the city, there are 8 locations. Tent, where you spawn, supermarket, metro which can
teleport you to another location in the city, pharmacy, police station, gas station,
residential building and car park.
There are also 7 items in the game, some are used for trading, some are used for using the
car and one of them is a bag, to increase the carrying capacity. There are 5 items which can
be picked up directly from the ground are a sandwich and a water bottle from the
supermarket, a med kit from pharmacy, and a knife and a bag from police station. There is
also an NPC called stranger which spawns in gas station and rotates from gas station to
residential building. When having the med kit and knife if you go to gas station and when
stranger is there, you can type “trade stranger items” which removes med kit and knife
from your inventory and replaces them with gasoline. When having the sandwich and water
bottle and if you go to residential building and when stranger is there, you can trade items
with stranger by typing “trade stranger items” again which removes sandwich and water
bottle from your inventory replacing them with car engine. (A reminder that you cannot
trade medkit and knife in residential building. It has to be the gas station.) After that, you
can go to car park and use the car by typing “use” and the game will take you to shelter, the
end point location outside of the city and the game will end here. However, there are a lot
of items, and you cannot carry them at the same time, so you need to pick up a bag from
the police station.
Now it’s time to explain how I manage to complete the base tasks. The first one was easy, I
looked up the base code locations and replaced them with my locations. In the Game
class, I introduced all the locations that I want to be in my game, and I gave them “Room”
data type which is an object of my Room class. In the createRooms() method I created
roomName = new Room (string explanation, string roomName), I created a map in my
head, making sure which room is where and how to go in between them. Then I set exit to
all the rooms in my game.
The second task, items. I created an Item class and used item data type for this task. Still
inside the createRooms() method, I introduced the items that want to be in my game,
created them by typing: “Item itemName = new Item(string itemName)”
. Then I created a
method called addItemToRoom(Room room, Item item) which adds my item to the room
by using Sets.
The third task, items and their weights. Inside my Item class, I used a hashMap<itemName,
itemWeight> and gave weight to items. Then I created another class, Inventory. Inside this
Inventory class, I introduced an integer named max_weight, which is 10 for the start of the
game. I made an Inventory method pickupItem(String itemName). When you type “pickup
‘itemName’” for example, “pickup sandwich” inside the room that sandwich locates, it
picks up sandwich into your inventory with its weight. If the item that we are picking up is a
bag, it increases max_weight to 20. Now we can carry all the items in the game.
The fourth task is the win condition. As I said, the goal of the game is to go to the shelter.
When we are in the shelter, the game congratulates you and the game is closed. To remind
the way to go to the shelter. We must pick up all the items in the ground, trade them with
the stranger (items that we give and stranger gives us change by the location. We must be
in the right location to execute certain trades) and get gasoline and a car engine. After that
we type “use” in the car park, and it takes us to the shelter.
The fifth base task, “back” method. In the Game method, I introduced an array list named
roomHistory, which takes Room objects. In the “goRoom” I edited it in a way that, every
time we go to a room, it adds that room into roomHistory (besides metro room). When we
call the back() method, it deletes the last element in the roomHistory and makes the
current room the last element after that removal. Which teleports us to the last room we
were in.
The sixth and final base task was adding new methods in the game. In my game I have 11
methods in total. Their names are “help, go, quit, pickup, inspect, remove, look, trade,
back, use, and backpack”.
Help method prints the command words that we can use. Go method makes us go to the
direction that we type after “go”, quit method finishes the game. The pickup method is
used as “pickup sandwich” which picks up the item off the ground if we are in the same
room with the item. Inspect method is used as. “inspect sandwich” which returns the
weight of the item. If the item is not in our inventory, it explains that we do not have it.
Remove method is used as “remove sandwich” which removes an item that is currently in
our inventory, reducing the total weight that we are carrying. If the item is not found, it
explains that we do not have that item. The Look method is quite a useful method. After
saying “look” it says where we are, which items are inside the room and if the stranger is
here or not. I’ve explained the back() method. The use method is using gasoline and car
engine inside car park. If we don’t have the items, it says that we do not have those items.
If we have those items but we are not in the car park, it tells us that we need to be in the car
park. If all conditions are met, it takes us to the shelter and the game ends. The backpack
method returns all the items we carry and the total weight we carry, if we don’t have any
items, it tells us that our inventory is empty.
The first challenge task was adding a character to the game. I’ve added the stranger who
spawns in gas station and moves to residential building and back every 3 time we go to a
room. I added integer player movement count, which is defined as 0, and every time we go
to a room, this count is increased by 1. If the count is divisible by 3, the stranger moves.
When the stranger is in the gas station, we can trade a med kit and knife to receive
gasoline. When the stranger is in the residential building, we can trade a sandwich and
water bottle to receive car engine.
The second task was extending the parser to three-word commands. I added the third word
in Parser and Command classes. I made the code work normally before this three-worded
command. However, if the first two words are “trade stranger”, the parser reads the third
word. In the process command method, I wrote if the three words that we type is “trade
stranger items”, it calls the tradeItems method. And the tradeItems method is a method
that understands where stranger is, if we have necessary items for the location, it removes
our items and gives us the needed car part, which is gasoline and car engine.
The third task was adding a room that teleports us. I’ve created a metro room just like any
other room in the game in createRooms method and I entered how to go to the metro by
setting exit for other rooms to metro, however I did not enter metro’s setExit. Instead,
inside of the goRoom method, I said if are in a room for example supermarket. If we say “go
east” in supermarket (where metro locates) it takes us to a random room from other rooms
inside the city (not the shelter and metro itself). I did this by creating an array that has the
possible rooms that we can go to from the metro, I imported random() method, and said
next room is a room randomly taken from this array.
To make my code easy to understand and manageable I made the following improvements.
When doing this project, I made a lot of changes in the code, making sure that making a
change won’t affect something that I did. For example, I used a hashMap in Item class that
takes the information of items and adds to the game. It is easily editable. If I want to add a
new item, I just enter a new item with its weight, and inside the createRoom, method I call
addItemToRoom method with the location that I want to add it. By this my code becomes
not only easily maintainable but also has high cohesion since storing the item datas in Item
class won’t affect the Inventory class which is related to Item class. The items I’ve created
are responsible driven design, since adding an item to your inventory can change the
capacity of your inventory, like the bag item.
Now as I’ve explained my code let’s make a walkthrough. We create a game class
instance, and we call void play() method. Now the game has started, and we are in the tent.
We go east to supermarket by typing “go east”. Inside the supermarket there are items. We
say “look” to see the items, and we see water_bottle and sandwich. We type “pickup
sandwich” and “pickup water_bottle”. Now, as we have the sandwich and water_bottle
which are necessary items for trading to get car engine. We go south twice from
supermarket and go to residential building. We type “look” to see if the stranger is here.
Luckly he is here. Now we type “trade stranger items” and get the car engine. Now we are
running low in carrying space. So, we go north once and then east, from the residential
building to the police station. Now we type “look” to see what’s around, and we see the
bag item. First, we say “pickup bag” which increases the carrying capacity to 20kg. Now we
also pick up knife by saying “pickup knife”. Now we go west from the police station to the
pharmacy. We type “look” and see med kit. We type “pickup medkit”. Now we have medkit
and knife to trade with the stranger to get gasoline. We go south, then west from the
pharmacy to the gas station. We type “look” and realize stranger is not here. The stranger
makes a move every 3 times we go somewhere. So, we just need to move to some other
room and come back. I type “go north” and then “go south”. After that I check if the
stranger is here by typing “look” and he is here. So, I type “trade stranger items” and
receive gasoline. Now we have to go to the car park. We say “go east” twice and we are in
the car park with gasoline and car engine. Now we say “use”, the game congratulates us,
we win by going to the shelter, and the game ends.
I haven't used the metro, since it randomly locates us somewhere. Instead, I walked
through every room, but you can go to metro and try your luck with it.
