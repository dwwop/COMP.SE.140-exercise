using System;
using System.Linq;
using System.Threading;
using SystemInfoService2DotNet.Models;
using SystemInfoService2DotNet.Services;

namespace SystemInfoService2DotNet.Facades;

/// <summary>
///     System Info Facade
/// </summary>
/// <param name="systemInfoService"></param>
public class SystemInfoFacade(ISystemInfoService systemInfoService) : ISystemInfoFacade
{
    public SystemInfoDTO GetSystemInfo()
    {
        var ipAddress = systemInfoService.GetIpAddress();
        var processes = systemInfoService.GetRunningProcesses()
            .Select(x => new ProcessDTO(Convert.ToInt32(x[0]), x[1], x[2], x[3])).ToList();
        var diskSpace = systemInfoService.GetDiskSpace();
        var uptime = systemInfoService.GetUptime();

        return new SystemInfoDTO(ipAddress, processes, diskSpace, uptime);
    }

    public void Shutdown()
    {
        new Thread(() =>
        {
            Thread.Sleep(2000);
            Environment.Exit(0);
        }).Start();
    }
}