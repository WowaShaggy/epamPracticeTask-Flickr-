Narrative: I should create a framework for testing site flickr

Lifecycle:
Before:
Given I choose browzer 'Chrome'
Given I login as 'wowashaggy@yahoo.com' with password 'mylife4aiur'

Scenario: I should see coincidence of url
Then I should check url

Scenario: I should check title of Home page
Then I should check title

Scenario: I should check number of menu's items
Then I should check number of menu's items

Scenario: I should check names of menu's items
Then I should compare names of menu's items

Scenario: I should check that Sub-menu items contain links
Then I should check that Sub-menu items contain links

Scenario: I should go to [You]-link and check username
When I go to [You]-link
Then I should compare username with 'Wowa Popeka'

Scenario: I should go to [Explore]-link and check photos with label '<photo_title> by <author>'
Given I am on PhotoPage
When I go to [Explore]-link
Then I should check photos

Scenario: I should check that photo and its title navigate to the same page with other picture details
Given I am on ExplorePage
When I go to DetailsPage from photo
When I go to DetailsPage from title
Then I should compare urls