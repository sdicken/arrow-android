CREATE TABLE [dbo].[Orders]
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [SubmitTime] TIMESTAMP NOT NULL, 
    [PickUpTime] TIMESTAMP NULL, 
    [DeliveryTime] TIMESTAMP NULL, 
    [DriverId] INT NOT NULL, 
    [ClientId] INT NOT NULL, 
    [OrderTotal] MONEY NOT NULL
)
