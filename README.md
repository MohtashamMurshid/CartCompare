# Mobile Application Develeopment Final Project
# Cartcompare

# (Please reach out to me if u need any help)
This project is a starter template for our mobile app with a basic layout. It includes **four different fragments** that each of us will develop individually. Each fragment represents a section of the app, allowing us to work independently and then bring it all together.

### Fragments in the Project
- **Fragment 1:** Home
- **Fragment 2:** Add
    ### Sean (Indivisual Cart Comparision) and Ines (Add or Drop)  
- **Fragment 3:** Cart
    ### Vesim and Moha ( Total Cart Price Comparision)
- **Fragment 4:** Profile





## Getting Started with the Project

### Step 1: Set Up Git
1. **Install Git** (if you haven’t already):
   - **Windows:** Download Git from [https://git-scm.com/downloads](https://git-scm.com/downloads) and follow the installation steps.
   - **Mac:** Git is usually pre-installed on Mac. You can confirm by opening Terminal and typing `git --version`.
   - **Linux:** Open Terminal and use the command: `sudo apt-get install git`.
2. **Set Up GitHub**:
   - Make sure you have a GitHub account and are added to the repository.

### Step 2: Clone the Repository
1. Go to our project’s GitHub page and find the green **Code** button.
2. Copy the HTTPS link under **Clone**.
3. Open **Android Studio** and select **File > New > Project from Version Control**.
4. Paste the link and click **OK**. This will download the project files to your computer.

### Step 3: Open the Project in Android Studio
1. Once the project is cloned, **Android Studio** should open it automatically.
2. Android Studio will detect missing dependencies and ask if you want to install them. **Accept all prompts** for Gradle sync and dependencies.

---

## Setting Up Dependencies
To make sure everything works smoothly, let’s make sure all libraries and dependencies are installed:
1. **Check Dependencies**:
   - Go to **File > Project Structure > Dependencies** and look for any warnings or missing items.
   - Click **Install** if you see any prompts.
   
2. **Sync Gradle**:
   - In the top right corner of Android Studio, you’ll see an **"Sync Project with Gradle Files"** option. Click it to ensure all dependencies are set up.

3. **Build the Project**:
   - Click **Build > Rebuild Project**. This will check for any issues and prepare the app to run on an emulator or device.

---

## Using Git: Branches and Commits

To keep our work separate and avoid conflicts, we’ll each work in our own Git branch.

### Step 4: Create Your Branch
1. **Open the terminal in Android Studio**:
   - Go to **View > Tool Windows > Terminal** or press **Alt + F12**.
2. **Create a branch for your work**:
   - Type this command, replacing `[Your_Name]` with your name or assigned fragment:
     ```bash
     git checkout -b [Your_Name]
     ```
   - Example: `git checkout -b Sarah` (This creates a new branch called **Sarah**).
3. **Push your branch to GitHub** so others can see it:
   - Type:
     ```bash
     git push -u origin [Your_Name]
     ```
   - This command saves your branch to GitHub.

### Step 5: Start Working on Your Fragment
Now, go to your fragment file in Android Studio and start building the features assigned to you. 

- **Save regularly** by pressing **Ctrl + S**.
- **Test your changes** on an emulator or physical device by clicking **Run** in the top toolbar.

### Step 6: Make Commits to Save Progress
When you’re ready to save a snapshot of your work:
1. **Stage Changes**:
   - In **Git > Commit...**, select the files you’ve worked on.
2. **Write a commit message** (a short description of what you’ve done, like "Created layout for Fragment 2").
3. **Commit and Push**:
   - After committing, click **Push** to upload your work to GitHub.

### Step 7: Regularly Pull Updates
To keep your branch up-to-date with everyone else’s work:
1. Switch to the main branch:
   ```bash
   git checkout main
   ```
2. Pull the latest changes:
   ```bash
   git pull origin main
   ```
3. Switch back to your branch:
   ```bash
   git checkout [Your_Name]
   ```
4. **Merge any updates** from main to your branch to make sure you’re working with the latest code:
   ```bash
   git merge main
   ```

---

## Tips for Team Collaboration
- **Pull from main frequently** to make sure you’re not missing any updates.
- **Communicate regularly**: If you’re making big changes or finishing your work, let the team know.
- **Ask for help**: If you get stuck, reach out. It’s better to solve issues together than to struggle alone.

Let’s work together to make this project a success!
