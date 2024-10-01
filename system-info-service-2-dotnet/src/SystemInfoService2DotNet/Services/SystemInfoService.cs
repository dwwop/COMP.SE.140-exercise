using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;

namespace SystemInfoService2DotNet.Services;

public class SystemInfoService : ISystemInfoService
{
    public string GetIpAddress()
    {
        return Dns.GetHostAddresses(Dns.GetHostName())
            .FirstOrDefault(ip => ip.AddressFamily == AddressFamily.InterNetwork)
            ?.ToString();
    }

    public List<string[]> GetRunningProcesses()
    {
        var process = new Process();
        process.StartInfo.FileName = "/bin/sh";
        process.StartInfo.Arguments = "-c \"ps\"";
        process.StartInfo.RedirectStandardOutput = true;
        process.Start();
        var output = process.StandardOutput.ReadToEnd()
            .Split('\n', StringSplitOptions.RemoveEmptyEntries)
            .Skip(1)
            .Select(x => x.Trim().Split(Array.Empty<char>(), StringSplitOptions.RemoveEmptyEntries).Take(4).ToArray())
            .ToList();
        process.WaitForExit();
        return output;
    }

    public long GetDiskSpace()
    {
        var drive = new DriveInfo("/");
        return Convert.ToInt64(drive.AvailableFreeSpace / (1024 * 1024));
    }

    public double GetUptime()
    {
        var uptimeProcess = new Process();
        uptimeProcess.StartInfo.FileName = "/bin/sh";
        uptimeProcess.StartInfo.Arguments = "-c \"cat /proc/uptime\"";
        uptimeProcess.StartInfo.RedirectStandardOutput = true;
        uptimeProcess.Start();
        var uptimeOutput = uptimeProcess.StandardOutput.ReadToEnd();
        uptimeProcess.WaitForExit();
        return Convert.ToDouble(uptimeOutput.Split(" ")[0]);
    }
}