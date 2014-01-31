CREATE TABLE [dbo].[GPSUpdates]
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [DriverId] INT NOT NULL, 
    [Latitude] FLOAT NOT NULL, 
    [Longitude] FLOAT NOT NULL, 
    [Timestamp] TIMESTAMP NOT NULL, 
    CONSTRAINT [FK_GPSUpdates_Drivers] FOREIGN KEY ([DriverId]) REFERENCES [Drivers]([UserId])
)

GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The unique id of the GPS update',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'GPSUpdates',
    @level2type = N'COLUMN',
    @level2name = N'Id'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The id of the driver that this location belongs to',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'GPSUpdates',
    @level2type = N'COLUMN',
    @level2name = N'DriverId'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The latitudnal value of the location of the driver',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'GPSUpdates',
    @level2type = N'COLUMN',
    @level2name = N'Latitude'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The longitudnal value of the location of the driver',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'GPSUpdates',
    @level2type = N'COLUMN',
    @level2name = N'Longitude'
GO
EXEC sp_addextendedproperty @name = N'MS_Description',
    @value = N'The time at which this data was recorded',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'GPSUpdates',
    @level2type = N'COLUMN',
    @level2name = N'Timestamp'