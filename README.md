<h1>👾 Please Read</h1>

<p>This is the start of the project. Akshaj has added a temp README file file for everyone to read.</p>

<p><strong>This is what everyone has to do to complete the project w/o major fuckups to the working files</strong></p>

There is a master branch and a development branch
DO NOT make changes to the master branch

<p>Clone the main repo with</p>

`git clone https://github.com/Shop-N-Split/Shop-N-Split.git`

<p>Start the git process</p>

`git init`

<p>Pull all the contents of the master branch to your local environment</p>
<p>This will update all the files from master on GitHub to the master locally</p>

`git pull origin master`

<p>Once you have the file and stuff done, change the git head to the development branch</p>

`git checkout -b development`

<p>Because there are other people simultaneously working on the code, we have a few extra steps</p>
<p>We will make another branch where we will do our own work</p>
<p>For example, if I am making the login page, I will create something like this:</p>
 
`git checkout -b login-page`
 
<p>Once your work is done, you will need to update the contents on the remote. </p>
```
git add .
git commit -m "your comment on what you have done"
git fetch origin
git checkout development //Change to the development branch
git merge login-page //Merge login-page to the development branch
git pull origin development //update the files from development becuase there might have been changes since you started working 
git push origin development //push changes to github
git branch -d login-page //delete the login-page branch
```

<p>Steps are similar for merging to the master, by using pull requests and shit like that. Will explain later.</p>
 
 
