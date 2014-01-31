CREATE TABLE [dbo].[Drivers]
(
	[UserId] INT NOT NULL PRIMARY KEY, 
    [Active] BIT NOT NULL DEFAULT 0, 
    [CurrentOrderId] INT NULL, 
    [LastOrderId] INT NULL, 
    [CurrentVehicle] TEXT NULL, 
    [DisplayName] TEXT NULL, 
    [CurrentLocation] TEXT NULL, 
    [LastUpdate] TIMESTAMP NULL
)

GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The user id of the driver.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Drivers',
    @level2type = N'COLUMN',
    @level2name = N'UserId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'A boolean value stating whether or not the driver is servicing orders.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Drivers',
    @level2type = N'COLUMN',
    @level2name = N'Active'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The id of the order the driver is currently servicing',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Drivers',
    @level2type = N'COLUMN',
    @level2name = N'CurrentOrderId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The id of the last order the driver serviced',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Drivers',
    @level2type = N'COLUMN',
    @level2name = N'LastOrderId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The description of the vehicle the driver is using (eg: ''car'', ''bike'', ''moped'', ''foot'')',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Drivers',
    @level2type = N'COLUMN',
    @level2name = N'CurrentVehicle'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The driver name to display to clients',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Drivers',
    @level2type = N'COLUMN',
    @level2name = N'DisplayName'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The current gps coordinates of the driver in the form ''{double},{double}''',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Drivers',
    @level2type = N'COLUMN',
    @level2name = N'CurrentLocation'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The timestamp of the last time the driver was updated',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'Drivers',
    @level2type = N'COLUMN',
    @level2name = N'LastUpdate'