# Mobile Application Develeopment Final Project
# Cartcompare
---
# Inas: Add Fragment & Item Details

_Here's what I already did_

### **1. Add Fragment:** 

![image](https://github.com/user-attachments/assets/c6d638a4-168a-4540-855d-ba5e35b01c0b)

- Fruits Fragment is a sub fragment embedded inside Add Fragment. 
- In Fruits Fragment, if "+" button is clicked, notificiation "apples added to cart" is shown
- if the card of Apples/Mangoes/etc is selected, app navigates to Item Details Fragment
- **I've only completed Fruit category**
  
### **2. Item Details:** 

![image](https://github.com/user-attachments/assets/b3cbe336-47db-404e-bd94-feee5dacedfd)
- this was supposed to be Sean's part but I accidentally overdid this woopsie, didn't realise it ;-;
- the " -  0  + " feature doesn't function yet.
- the sort by price feature works.

### **3. Extras:** 

a. "item.kt" is a data class acts as a blueprint for item details like itemName, aeonPrice, jayaPrice etc.. 

- you can see the AppleDetails/MangoDetails/etc are set up in "ItemDetailsFragment.kt" 

b. "FragmentSwitch.kt" is just an interface for navigation purposes

- implemented at MainActivity.kt to navigate to Item Details Fragment
  
---
## What's needed to be done next?

1. use ViewModel to store data of cart item details & quantity.
   
   - just chatgpt about it.
   - can use other solutions if better.
     
3. if (1) is completed, then we can make the " -  0  + " and "+" feature work. 
4. Vesim & Moha can start designing UI of Cart Fragment
   
_p/s: 1 & 2 seems like a fair task so maybe Sean will do this part_

---
## Basic Tips on How To Start

Each fragment comes along with a UI design: xml file.

All fragments will need some sorta binding process, to link code with its xml. so just follow these structures:

When making your fragment.kt, you'll need 3 of these basic setups: onCreateView(), onViewCreated(), onVeiwDestroyed()

**1. onCreateView():**

this is for binding your code.kt with ur design.xml file. here is example of my FruitsFragment.kt
  
        //basic binding set ups. just change yours to your xml file name:
        private var _binding: FragmentFruitsBinding? = null  //if file name is fragment_A.xml, write it as AFragmentBinding
        private val binding get() = _binding!!
    
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentFruitsBinding.inflate(inflater, container, false) //change here also
            return binding.root 
       
**2. onViewCreated():**

this is where you code all UI interactions here. you can now call your xml elements using "binding.yourElementId" 
     
      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

          //example
          binding.YourButton.setOnClickListener{
              //bla bla bla
          }
       }
       
**3. onViewDestroyed():**

for cleaning up any references or resources that might cause memory leaks.
        
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null // Prevent memory leaks
        }


_p/s: try to imitate the Figma UI design Moha made. You can just copy any elements or color codes from my xml layouts._

---

## Tips for Team Collaboration

- **Pull from main frequently** to make sure you’re not missing any updates.
- **Communicate regularly**: If you’re making big changes or finishing your work, let the team know.
- **Ask for help**: If you get stuck, reach out. It’s better to solve issues together than to struggle alone.

Let’s work together to make this project a success!
