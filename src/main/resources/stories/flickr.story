Narrative: I should create a framework for testing site flickr

Lifecycle:
Before:
Given I choose browzer 'Firefox'
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

Scenario: I should check that Page with photo details contains Title
Given I am on ExplorePage
When I go to DetailsPage
Then I should check Title of Photo

Scenario: I should check that Page with photo details contains Author
Given I am on ExplorePage
When I go to DetailsPage
Then I should check Author of Photo

Scenario: I should check that Page with photo details contains Follow-button
Given I am on ExplorePage
When I go to DetailsPage
Then I should check Follow-button below Photo

Scenario: I should check that Page with photo details contains Numbers of Views etc
Given I am on ExplorePage
When I go to DetailsPage
Then I should check Numbers of Views etc

Scenario: I should check that Page with photo details contains Date
Given I am on ExplorePage
When I go to DetailsPage
Then I should check Date of Photo

Scenario: I should check that Page with photo details contains Right-link
Given I am on ExplorePage
When I go to DetailsPage
Then I should check Rights of Photo

Scenario: I should check that Page with photo details contains Camera details
Given I am on ExplorePage
When I go to DetailsPage
Then I should check Camera details

Scenario: I should check that Page with photo details contains Tags details
Given I am on ExplorePage
When I go to DetailsPage
Then I should check Tags of Photo

Scenario: I should check that Author’s name navigates to the page with author’s photostream
Given I am on ExplorePage
When I go to DetailsPage
When I go to AuthorsPage
Then I should check Name on AuthorsPage

Scenario: I should do to Albums and check that every Album has a thumbnail, title etc
Given I am on ExplorePage
When I go to DetailsPage
When I go to AuthorsPage
When I go to Albums and check number of Albums
Then I should check every Album

Scenario: I should go to [You]->[Groups] and check Url
Given I am on ExplorePage
When I go to DetailsPage
When I go to AuthorsPage
When I go to GroupPage
Then I should check GroupPage Url

Scenario: I should check recommended group
Given I am on GroupPage
Then I should check recommended group

Scenario: I should go to [Explore]->[Galleries] and check Url
Given I am on GroupPage
When I go to GalleriesPage
Then I should check GalleriesPage Url

Scenario: I should check gallery's items
Given I am on GalleriesPage
Then I should check gallery's items

Scenario: I should testing Search functionality and check Url
Given I am on GalleriesPage
When I go to SearchPage
Then I should check SearchPage Url

Scenario: I should find photo with some name
Given I am on SearchPage
When I send request 'Red Panda'
Then I should check First Photo Name